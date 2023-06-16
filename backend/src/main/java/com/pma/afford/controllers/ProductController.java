package com.pma.afford.controllers;

import com.pma.afford.entities.Product;
import com.pma.afford.entities.UserFavourProduct;
import com.pma.afford.services.emailservice.EmailService;
import com.pma.afford.services.productservice.ProductService;
import com.pma.afford.services.userproductservice.UserProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
/**

 This class represents the RESTful API for managing products.

 It provides endpoints for getting products by name, category,

 price greater than a certain value, price lesser than a certain value,

 and product price chart by ID.

 The class is annotated with @RestController and @RequestMapping("/product").

 It also allows cross-origin requests with @CrossOrigin(origins = "*").
 */
@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")
public class ProductController {


    @Autowired
    ProductService productService;

    /**

     This endpoint retrieves a list of products by name.
     @param name The name of the product.
     @return List of Product objects that match the given name.
     */
    @GetMapping("/productname/{name}")

    public List<Product> getProductByName(@PathVariable("name") String name){

       return productService.getProductByName(name);
    }
    /**

     This endpoint retrieves a list of products by category.
     @param name The name of the category.
     @return List of Product objects that match the given category.
     */
    @GetMapping("/categories/{name}")
    public List<Product> getProductsByCategory(@PathVariable("name") String name){

        return productService.getProductByCategory(name);
    }
    /**

     This endpoint retrieves a list of products with price greater than a certain value.
     @param name The name of the product.
     @param value The minimum price value.
     @return List of Product objects with price greater than the given value.
     */
    @GetMapping("/productname/{name}/greaterthan/{value}")

    public List<Product> getProductGreaterThan(@PathVariable("name") String name,@PathVariable("value") int  value){

        return productService.getProductGreaterThan(name,value);
    }
    /**

     This endpoint retrieves a list of products with price lesser than a certain value.
     @param name The name of the product.
     @param value The maximum price value.
     @return List of Product objects with price lesser than the given value.
     */
    @GetMapping("/productname/{name}/lesserthan/{value}")

    public List<Product> getProductLesserThan(@PathVariable("name") String name,@PathVariable("value") int  value){

        return productService.getProductLesserThan(name,value);
    }
    /**

     This endpoint retrieves the price chart of a product by ID.
     @param id The ID of the product.
     @return HashMap containing the price history of the product.
     */
    @GetMapping("/productname/pricechart/{id}")

    public HashMap<String,List<String>> getProductPriceChartByID(@PathVariable("id") String id){

        return productService.getProductPriceChartByID(id);
    }

}

