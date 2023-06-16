package com.pma.afford.entities;
import jakarta.persistence.*;
/**

 This is a model class for the product scraped from a website.
 It is annotated with JPA annotations for entity mapping to a database table.
 */
@Entity
@Table(name = "productWebScrap")
public class ProductScrap {
    /**

     The unique identifier for the product, name of the product, title of the product,
     price of the product, link to the product, link to the product's image, date when the product was fetched,
     ategory of the product,ame of the store where the product is sold
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "productName", nullable = true, length = 200)
    private String productName;
    @Column(name = "productTitle", nullable = true, length = 200)
    private String productTitle;

    @Column(name = "productPrice", nullable = true, length = 200)
    private String productPrice;
    @Column(name = "productLink", nullable = true, length = 200)
    private String productLink;
    @Column(name = "imageLink", nullable = true, length = 200)
    private String imageLink;
    @Column(name = "fetchDate", nullable = true, length = 200)
    private String fetchDate;
    @Column(name = "category", nullable = true, length = 200)
    private String category;
    @Column(name = "store", nullable = true, length = 200)
    private String store;

    public ProductScrap(){super();};

    /**

     Constructor for the product with all attributes.
     @param productId The unique identifier for the product.
     @param productName The name of the product.
     @param productTitle The title of the product.
     @param productPrice The price of the product.
     @param productLink The link to the product.
     @param imageLink The link to the product's image.
     @param fetchDate The date when the product was fetched.
     @param category The category of the product.
     @param store The name of the store where the product is sold.
     */
    public ProductScrap(Long productId, String productName, String productTitle, String productPrice, String productLink, String imageLink, String fetchDate, String category, String store) {
        super();
        this.productId = productId;
        this.productName = productName;
        this.productTitle=productTitle;
        this.productPrice = productPrice;
        this.productLink = productLink;
        this.imageLink=imageLink;
        this.fetchDate=fetchDate;
        this.category=category;
        this.store=store;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getFetchDate() {
        return fetchDate;
    }

    public void setFetchDate(String fetchDate) {
        this.fetchDate = fetchDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public Long getProductId() {
        return productId;
    }


    public void setProductId(Long productId) {
        this.productId = productId;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductLink() {
        return productLink;
    }

    public void setProductLink(String productLink) {
        this.productLink = productLink;
    }
}
