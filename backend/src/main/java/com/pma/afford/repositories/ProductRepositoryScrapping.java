package com.pma.afford.repositories;

import com.pma.afford.entities.ProductScrap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
/**

 Repository interface for performing CRUD operations on ProductScrap entity
 */
@Repository
public interface ProductRepositoryScrapping extends JpaRepository<ProductScrap,Long> {

    /**

     This repository method retrieves a list of ProductScrap objects from the database that have a product_title column
     value matching the provided name parameter. It uses a native SQL query to perform the search.
     @param name A String representing the name of the product(s) to be retrieved.
     @return A List of ProductScrap objects that have a product_title column value matching the provided name parameter.
     */
    @Query(
            value = "SELECT * FROM product_web_scrap p WHERE p.product_title = :name",
            nativeQuery = true)
    List<ProductScrap> findByProductName(String name);

    /**

     This repository method retrieves a list of ProductScrap objects from the database that have a productTitle column
     value matching the provided name parameter. It uses Spring Data's method naming convention for defining the query.
     @param name A String representing the name of the product(s) to be retrieved.
     @return A List of ProductScrap objects that have a productTitle column value matching the provided name parameter.
     */
    List<ProductScrap> findByproductTitle(String name);



}
