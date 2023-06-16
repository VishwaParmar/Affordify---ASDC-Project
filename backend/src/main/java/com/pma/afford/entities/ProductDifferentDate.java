package com.pma.afford.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
/**

 This class represents a product with different dates and corresponding prices.
 */
@Entity
@Table(name = "productwithdifferentdate")
public class ProductDifferentDate {
    /**

     The unique identifier of the product date,
     date of the product, price of the product on the specified dateID of the product that the date and price belongs to.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dateID;

    @Column(name = "date", nullable = false, length = 45)
    private String date;

    @Column(name = "price", nullable = false, length = 45)
    private String price;

    @Column(name = "productID", nullable = false, unique = false, length = 45)
    private String productID;


    public ProductDifferentDate() {
        super();
    }
    /**

     Constructs a new ProductDifferentDate object with the given parameter values.
     @param dateID The unique ID of the date.
     @param date The date of the price.
     @param price The price of the product on the given date.
     @param productID The ID of the product associated with the date and price.
     */
    public ProductDifferentDate(int dateID, String date, String price, String productID) {
        this.dateID = dateID;
        this.date = date;
        this.price = price;
        this.productID = productID;
    }

    public int getDateID() {
        return dateID;
    }

    public void setDateID(int dateID) {
        this.dateID = dateID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }
}
