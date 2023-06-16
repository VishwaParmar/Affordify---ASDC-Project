package com.pma.afford;

import com.google.gson.Gson;
import com.pma.afford.configuration.RSAKeyProperties;
import com.pma.afford.entities.ProductScrap;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
/**
 * This class provides functionality to scrape product data from various websites and add it to a database,
 * after authenticating the user via a provided token.
 */
public class WebScrapping {

    /**
     * Authenticates the user by making a GET request to the login endpoint of the web application,
     * using the provided email and password credentials encoded in Base64.
     * @return A token representing the user's authentication status.
     * @throws IOException If an I/O error occurs while making the HTTP request.
     */
    public static String testAuth() throws IOException {
        URL url = new URL ("http://csci5308vm24.research.cs.dal.ca:8085/afford/login");
        String encoding = Base64.getEncoder().encodeToString(("affordify@gmail.com:kova").getBytes("UTF-8"));

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.setRequestProperty  ("Authorization", "Basic " + encoding);
        InputStream content = (InputStream)connection.getInputStream();
        BufferedReader in   =
                new BufferedReader (new InputStreamReader(content));
        String token=in.readLine();
        return token;
    }


    /**
     * Scrapes product data from the Costco, Walmart, and Sobeys websites for the provided keywords and adds the
     * scraped data to the database using the provided authentication token.
     * @param token A token representing the user's authentication status.
     */
    public static void scrapCostcoWallmartSobeysAddToTable(String token){
        String products[][]=new String[2][4];
        products[0][0]="Vegetable";
        products[0][1]="tomato";
        products[0][2]="onion";
        products[0][3]="ginger";
        products[1][0]="Meat";
        products[1][1]="chicken";
        products[1][2]="pork";
        products[1][3]="fish";

        for (int i=0;i<products.length;i++){
            String category =products[i][0];
            for (int j=1;j<products[i].length;j++){
                String keyword=products[i][j];
                scanItemsCostco(keyword,category,token);
                scanItemsSobeys(keyword,category,token);
                scanItemsWallmart(keyword,category,token);
            }
        }

        triggerEmail(token);
    }
    /**

     Sends a GET request to trigger an email notification for the user.

     @param token The authentication token of the user.
     */

    public static void triggerEmail(String token){

        try {
            URL url = new URL("http://csci5308vm24.research.cs.dal.ca:8085/sendemail/sendfavoriteemail"); // the URL to send the GET request to
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET"); // set the request method to GET
            int timeout=5000;
            connection.setConnectTimeout(timeout); // set a connection timeout of 5 seconds
            connection.setReadTimeout(timeout); // set a read timeout of 5 seconds
            connection.setRequestProperty("Authorization", "Bearer "+token); // set the bearer token

            int status = connection.getResponseCode(); // get the response status code
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**

     Adds the data of a scrapped product to the database.
     @param product The scrapped product object to be added to the database.
     @param token The authentication token of the user.
     */
    public static void addData(ProductScrap product,String token){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("http://csci5308vm24.research.cs.dal.ca:8085/scrap/add");

        ProductScrap productScrap=new ProductScrap();
        productScrap=product;
        Gson gson = new Gson();
        String client = gson.toJson(productScrap);

        // For authorization
        // https://stackoverflow.com/questions/3283234/http-basic-authentication-in-java-using-httpclient
        httppost.setHeader("Content-Type","application/json");
        httppost.setHeader("Authorization", "Bearer " + token);
        httppost.setEntity(new StringEntity(client, ContentType.APPLICATION_JSON));

        //Execute and get the response.
        HttpResponse response = null;
        try {
            response = httpclient.execute(httppost);
        } catch (IOException e) {
//                throw new InternalServerErrorException("Post fails");
            System.out.println("fails");
        }
    }

    /**
     Scans the Costco website for a given product keyword and category and returns a ProductScrap object with the
     information of the first matching product found.
     @param keyword the keyword to search for on the Costco website
     @param category the category to filter the search results on the Costco website
     @return a ProductScrap object containing information of the first matching product found on the Costco website
     */
    public static void scanItemsCostco(String keyword, String category,String token) {
        String url = "https://www.costco.ca/CatalogSearch?dept=All&keyword=";
        int child0=0;
        int child1=1;
        int child2=2;
        int child3=3;

        String costcoKeyword = new String(keyword);
        String costcoCategory=new String(category);
        Document document = null;
        try {
            document = Jsoup.connect(url + costcoKeyword).get();
        } catch (Exception e) {
            System.out.println(e);
        }
        Elements elements=document.getElementsByClass("thumbnail");
        ProductScrap products=new ProductScrap();
        for(Element e:elements) {
            try {
                String nameOfProduct = e.child(child1).child(child3).child(child0).text();
                String priceOfProduct = e.child(child1).child(child2).child(child0).text();
                String linkOfProduct = e.child(child1).child(child3).child(child0).attributes().get("href");
                String linkOfImage = e.child(child0).child(child0).children().attr("src");

                products.setProductName(nameOfProduct);
                products.setProductPrice(priceOfProduct);
                products.setProductLink(linkOfProduct);
                products.setImageLink(linkOfImage);
                products.setCategory(costcoCategory);
                products.setProductTitle(keyword);
                products.setStore("costco");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDateTime now = LocalDateTime.now();
                products.setFetchDate(dtf.format(now).toString());

                addData(products,token);
            } catch (Exception e2) {
                System.out.println(e2 + "Not fetched");
            }
        }
    }

    /**

     Scans the Walmart website for a given product keyword and category and returns a ProductScrap object with the
     information of the first matching product found.
     @param wallmartKeyword the keyword to search for on the Walmart website
     @param wallmartCategory the category to filter the search results on the Walmart website
     @return a ProductScrap object containing information of the first matching product found on the Walmart website
     */

    public static void scanItemsWallmart(String wallmartKeyword,String wallmartCategory,String token){

        System.setProperty("http.proxyhost", "128.o.o.1");
        System.setProperty("http.proxyport", "3129");

        String searchItem = wallmartKeyword;

        String walmartURL = "https://www.walmart.ca/search?q=" + searchItem;
        ProductScrap products=new ProductScrap();

        try {
            Document document = Jsoup.connect(walmartURL).get();
            Elements body = document.select("div.css-d6xzj5.e5icx9n1");
            Set<String> itemNames = new HashSet<>();
            for (Element element : body.select("div")) {
                try {
                    if (!element.select("a.css-770c6j.epettpn1").attr("href").trim().isEmpty()) {
                        String itemLink = "https://www.walmart.ca" + element.select("a.css-770c6j.epettpn1").attr("href");
                        itemLink = itemLink.strip();
                        if (!itemNames.contains(itemLink)) {
                            itemNames.add(itemLink);
                            String itemName = element.select("a.css-770c6j.epettpn1").attr("aria-label");
                            String imageSRC = element.select("img.css-19q6667.e175iya62").attr("src");

                            // For analysis purpose : Placing random value as price
                            Random num = new Random();
                            int predictPrice=10;
                            int minimum_price=5;
                            String itemPrice = Integer.toString(num.nextInt(predictPrice) + minimum_price);
                            products.setProductName(itemName);
                            products.setProductPrice("$" + itemPrice);
                            products.setProductLink(itemLink);
                            products.setImageLink(imageSRC);
                            products.setCategory(wallmartCategory);
                            products.setProductTitle(wallmartKeyword);
                            products.setStore("Wallmart");
                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                            LocalDateTime now = LocalDateTime.now();
                            products.setFetchDate(dtf.format(now).toString());
                            addData(products,token);
                        }
                    }
                }
                catch (Exception e){
                    System.out.println(e);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**

     Web-scraping method for Sobeys store.
     @param sobeysKeyword the search keyword for the product
     @param sobeysCategory the category of the product
     @return a ProductScrap object containing information about the scraped product
     */
    public static void scanItemsSobeys(String sobeysKeyword,String sobeysCategory, String token){
        String url="https://www.sobeys.com/en/?s="+sobeysKeyword+"&fwp_keyword_search="+sobeysKeyword+"&lang=en";
        Document document=null ;
        ProductScrap products=new ProductScrap();
        try{
            String item="";
            document=Jsoup.connect(url+item).get();
        }
        catch (Exception e){
            System.out.println(e);
        }

        Elements elements=document.getElementsByClass("row no-gutters");
        int child0=0;
        int elementPosition0=0;


        for(Element e:elements){
            try {
                String imageLink = e.child(child0).getElementsByTag("img").attr("src");
                String productName = e.getElementsByClass("card-title").tagName("a").text();
                String productLink = e.getElementsByClass("card-title").get(elementPosition0).getElementsByAttribute("href").get(elementPosition0).attributes().get("href");
                Random number = new Random();
                int predictPrice=10;
                int minimum_price=5;
                String productPrice = Integer.toString(number.nextInt(predictPrice) + minimum_price);
                products.setProductName(productName);
                products.setProductPrice("$"+productPrice);
                products.setProductLink(productLink);
                products.setImageLink(imageLink);
                products.setCategory(sobeysCategory);
                products.setProductTitle(sobeysKeyword);
                products.setStore("sobeys");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDateTime now = LocalDateTime.now();
                products.setFetchDate(dtf.format(now).toString());
                addData(products,token);
            }catch (Exception error){
                System.out.println(error);
            }
        }
    }
}
