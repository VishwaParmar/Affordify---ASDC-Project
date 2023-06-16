package com.pma.afford.controllers;

import com.pma.afford.entities.Product;
import com.pma.afford.entities.ProductScrap;
import com.pma.afford.services.productservicescrapping.ProductServiceScrapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
/**

 This class represents a REST controller for handling HTTP requests related to product scraping.

 It is mapped to the "/scrap" endpoint and allows for adding new products and retrieving products by name.
 */
@RestController
@RequestMapping("/scrap")
@CrossOrigin(origins = "*")
public class ProductControllerScrap {

    /**

     An instance of ProductServiceScrapping used for performing product scraping operations.
     */
    @Autowired
    ProductServiceScrapping productServiceScrapping1;

    /**

     Adds a new product to the database via the ProductServiceScrapping instance.
     @param product A ProductScrap object representing the new product to be added.
     @return The ProductScrap object that was added to the database.
     */
    @PostMapping("/add")
    public ProductScrap productAdd(@RequestBody ProductScrap product) {
        return productServiceScrapping1.saveNewProduct(product);
    }

    /**

     Retrieves a list of products from the database that have a name matching the provided parameter.
     @param name A String representing the name of the product(s) to be retrieved.
     @return A List of ProductScrap objects that have a name matching the provided parameter.
     */

    @GetMapping("/productname/{name}")

    public List<ProductScrap> getProductByName(@PathVariable("name") String name){

        return productServiceScrapping1.productByName(name);
    }



}