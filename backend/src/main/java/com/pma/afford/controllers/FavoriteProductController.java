package com.pma.afford.controllers;

import com.pma.afford.entities.Product;
import com.pma.afford.entities.UserFavourProduct;
import com.pma.afford.services.userproductservice.UserProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**

 The FavoriteProductController class handles HTTP requests related to the user's favorite products.
 It is annotated with @RestController and @RequestMapping to map the class to the "/favoriteproduct" endpoint in the application.
 The @CrossOrigin annotation allows cross-origin requests from any origin.
 This class has a dependency on the UserProductService class, which is injected using the @Autowired annotation.
 The UserProductService is responsible for managing the user's favorite products.
 */
@RestController
@RequestMapping("/favoriteproduct")
@CrossOrigin(origins = "*")
public class FavoriteProductController {

    @Autowired
    UserProductService userProductService;

    /**

     Saves a user's favorite product.
     This method is mapped to the "/favoriteproduct/savefavouriteproduct" endpoint using the @PostMapping annotation.
     @param userFavourProduct The UserFavourProduct object containing the user's favorite product.
     @return A String representing the status of the operation.
     */

    @PostMapping("/savefavouriteproduct")
    public String saveFavourProduct(@RequestBody UserFavourProduct userFavourProduct) {
        return userProductService.saveFaovouriteProduct(userFavourProduct);
    }

    /**

     Retrieves a user's favorite products.
     This method is mapped to the "/favoriteproduct/getfavouriteproduct/{usermail}" endpoint using the @GetMapping annotation.
     @param userMail The email of the user whose favorite products are to be retrieved.
     @return A ResponseEntity containing a list of Product objects.
     */
    @GetMapping("/getfavouriteproduct/{usermail}")
    public ResponseEntity<List<Product>> getFavourProduct(@PathVariable("usermail") String userMail) {
        return ResponseEntity.ok(userProductService.findFavourProducts(userMail));
    }
    /**

     Deletes a user's favorite product.
     This method is mapped to the "/favoriteproduct/deletefavoriteproduct/{usermail}/{productid}" endpoint using the @GetMapping annotation.
     @param userMail The email of the user whose favorite product is to be deleted.
     @param productID The ID of the favorite product to be deleted.
     */
    @GetMapping("/deletefavoriteproduct/{usermail}/{productid}")
    public  void deleteFavoriteProduct(@PathVariable("usermail") String userMail,@PathVariable("productid") int productID) {

        userProductService.deleteFavoriteById(productID,userMail);
    }

    /**

     Retrieves a user's favorite products based on a time difference between the current date and the product's added date.
     This method is mapped to the "/favoriteproduct/getfavoriteproduct/notification/{email}" endpoint using the @GetMapping annotation.
     @param email The email of the user whose favorite products are to be retrieved.
     @return A List of Product objects.
     */

    @GetMapping("/getfavoriteproduct/notification/{email}")

    public List<Product> getNotificationData(@PathVariable("email") String email){

        return  userProductService.getProductByDateDifference(email);
    }

}

