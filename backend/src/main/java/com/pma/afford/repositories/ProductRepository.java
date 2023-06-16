package com.pma.afford.repositories;

import java.util.*;
import com.pma.afford.entities.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
/**

 ProductRepository interface provides CRUD operations and additional query methods for the Product entity.
 */
public interface ProductRepository extends CrudRepository<Product, Integer> {
    /**

     Finds all products whose names contain the given string, ignoring case.
     @param Name The string to search for in the product names.
     @return A list of products whose names contain the given string.
     */
    List<Product> findByProductNameContainingIgnoreCase(String Name);
    List<Product> findByCategory(String Name);
    /**

     Finds all products with the given name whose price is greater than or equal to the given value.
     @param Name The name of the product.
     @param value The minimum price value.
     @return A list of products whose name is the given name and whose price is greater than or equal to the given value.
     */
    @Query(value="select * from productdetails name where name=:c and price >= :value",nativeQuery=true)
    List<Product> getProductGreaterThan(@Param("c") String Name, @Param("value") int value);

    /**

     Finds all products with the given name whose price is less than or equal to the given value.
     @param Name The name of the product.
     @param value The maximum price value.
     @return A list of products whose name is the given name and whose price is less than or equal to the given value.
     */
    @Query(value="select * from productdetails name where name=:c and price <= :value",nativeQuery=true)
    List<Product> getProductLesserThan(@Param("c") String Name,@Param("value") int value);

}
