package com.pma.afford.services.productservicescrapping;


import com.pma.afford.entities.ProductScrap;
import com.pma.afford.repositories.ProductRepositoryScrapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
    /**

        This class provides methods for saving and retrieving product data from the scraping database.
        */
@Service
public class ProductServiceScrapping {

    @Autowired
    ProductRepositoryScrapping productRepositoryScrapping;
        /**

         Saves new product data to the scraping database.
         @param productScrap The product data to be saved
         @return The saved product data
         */
    public ProductScrap saveNewProduct(ProductScrap productScrap){
        return productRepositoryScrapping.save(productScrap);
    }
        /**

         Retrieves product data by ID from the scraping database.
         @param id The ID of the product to retrieve
         @return An Optional containing the retrieved product data, or an empty Optional if no matching product is found
         */
    public Optional<ProductScrap> productById(long id){
        return productRepositoryScrapping.findById(id);
    }
        /**

         Retrieves product data by name from the scraping database.
         @param name The name of the product(s) to retrieve
         @return A List of ProductScrap objects containing the retrieved product data, or an empty List if no matching products are found
         */
        public List<ProductScrap> productByName(String name){
            List<ProductScrap> pList= productRepositoryScrapping.findByproductTitle(name);
            for(int i=0;i<pList.size();i++ )
            {
                System.out.println(pList.get(i).getProductId()+" "+pList.get(i).getProductLink()+" "+pList.get(i).getProductName());
            }
            return pList;
        }

}