package com.pma.afford.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
/**

 This class represents a product in the application.
 */
@Entity
@Table(name = "productdetails")
public class Product {
    /**

     The unique identifier of the product ID, name , ShopName, Price, link, category, RealName, ProductLink .
     */

    @Id
    private int ProductID;

    @Column(name = "name", nullable = false, length = 255)
    private String productName;

    @Column(name = "ShopName", nullable = false, length = 255)
    private String ShopName;

    @Column(name = "Price", nullable = false, length = 255)
    private int Price;

    @Column(name = "link", nullable = false, length = 255)
    private String link;

    @Column(name = "category", nullable = false, length = 255)
    private String category;

    @Column(name = "RealName", nullable = false, length = 255)
    private String RealName;
    @Column(name = "ProductLink", nullable = false, length = 255)
    private String ProductLink;
    /**

     Creates a new instance of the {@link Product} class.
     */
    public Product() {
        super();
    }
    /**

     Creates a new instance of the {@link Product} class with the specified properties.
     @param name The name of the product.
     @param ShopName The name of the shop where the product is sold.
     @param Price The price of the product.
     @param link The link to the product's webpage.
     @param category The category of the product.
     @param ProductLink The link to the product's image.
     @param RealName The real name of the product.
     */
    public Product(String name, String ShopName, int Price, String link, String category, String ProductLink, String RealName) {
        super();
        this.productName = name;
        this.ShopName = ShopName;
        this.Price = Price;
        this.link = link;
        this.category = category;
        this.ProductLink = ProductLink;
        this.RealName = RealName;
    }



    public int getProductID() {
        System.out.println("productid-->"+ProductID);
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public String getName() {
        return productName;
    }

    public void setName(String name) {
        this.productName = name;
    }

    public String getShopName() {
        System.out.println("ShopName-->"+ShopName);
        return ShopName;
    }
   /**

    Gets nad sets the unique identifier of the product, name, ShopName, Price, link, category, RealName, ProductLink .
    @return The unique identifier of the product, name, ShopName, Price, link, category, RealName, ProductLink .
    @param name The name of the product, name, ShopName, Price, link, category, RealName, ProductLink .
            */

    public void setShopName(String shopName) {
        ShopName = shopName;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String realName) {
        RealName = realName;
    }

    public String getProductLink() {
        return ProductLink;
    }

    public void setProductLink(String productLink) {
        ProductLink = productLink;
    }
}
