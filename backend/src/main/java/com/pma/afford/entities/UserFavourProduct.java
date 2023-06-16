package com.pma.afford.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
/**

 Entity class representing the User Favour Product table in the database.
 */
@Entity
@Table(name = "user_favour_product")
public class UserFavourProduct {
    /**

     The ID of the User Favour Product entry, email address of the user who has favourited the product,
     ID of the product that the user has favourited,
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favourID", nullable = false, length = 45)
    private Integer favourID;

    @Column(name = "userMail", nullable = false, length = 45)
    private String userMail;

    @Column(name = "productId", nullable = false, length = 45)
    private Integer productID;
    /**

     Default constructor for the UserFavourProduct class.
     */
    public UserFavourProduct() {
        super();
    }
    /**

     Constructor for the UserFavourProduct class.
     @param userMail the email address of the user who has favourited the product
     @param productID the ID of the product that the user has favourited
     */
    public UserFavourProduct(String userMail, Integer productID) {
        super();
        this.userMail = userMail;
        this.productID = productID;
    }



    public int getFavourID() {
        return favourID;
    }

    public void setFavourID(int favourID) {
        this.favourID = favourID;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }
}
