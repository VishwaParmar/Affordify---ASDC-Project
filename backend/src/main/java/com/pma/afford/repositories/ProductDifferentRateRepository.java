package com.pma.afford.repositories;

import com.pma.afford.entities.ProductDifferentDate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
/**

 Repository interface for ProductDifferentRate entity. Extends CrudRepository to provide basic CRUD operations.
 */

public interface ProductDifferentRateRepository extends CrudRepository<ProductDifferentDate, Integer> {
    /**

     Retrieves the prices of a product with a given product ID.
     @param id the product ID
     @return a list of ProductDifferentDate objects representing the prices of the product
     */
    @Query(value="select * from productwithdifferentdate name where productid=:c",nativeQuery=true)

    List<ProductDifferentDate> getpriceofproduct(@Param("c") String id);

    /**

     Retrieves a product with a given product ID and date.
     @param id the product ID
     @param date the date of the product
     @return a list of ProductDifferentDate objects representing the product
     */
    @Query(value="select * from productwithdifferentdate name where productid=:c and date=:d",nativeQuery=true)

    List<ProductDifferentDate> getproduct(@Param("c") String id,@Param("d") String date);
}