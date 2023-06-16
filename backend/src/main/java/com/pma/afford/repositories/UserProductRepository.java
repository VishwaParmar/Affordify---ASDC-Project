package com.pma.afford.repositories;

import java.util.List;

import com.pma.afford.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pma.afford.entities.UserFavourProduct;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
/**

 The UserProductRepository interface provides methods for accessing and manipulating UserFavourProduct data from the database.
 */
public interface UserProductRepository extends JpaRepository<UserFavourProduct, String> {
	/**

	 Returns a list of UserFavourProduct objects that match the given userMail.
	 @param userMail The email address of the user whose favored products are to be retrieved.
	 @return A list of UserFavourProduct objects.
	 */
	List<UserFavourProduct> findByUserMail(String userMail);

	/**

	 Determines if a UserFavourProduct object exists for the given userMail and productID.
	 @param userMail The email address of the user to check.
	 @param productID The ID of the product to check.
	 @return True if a UserFavourProduct object exists for the given userMail and productID, false otherwise.
	 */
	Boolean existsByUserMailAndProductID(String userMail, Integer productID);
	/**

	 Returns a list of UserFavourProduct objects that match the given favourID.
	 @param Name The ID of the favor to retrieve.
	 @param value The email address of the user whose favor is to be retrieved.
	 @return A list of UserFavourProduct objects.
	 */
	@Query(value="select * from user_favour_product where product_id=:c and user_mail = :value",nativeQuery=true)
	 List<UserFavourProduct> findByfavourID(@Param("c") int Name, @Param("value") String value);
	/**

	 Deletes all UserFavourProduct objects that match the given favourID.
	 @param favourID The ID of the favor to delete.
	 @return A list of the deleted UserFavourProduct objects.
	 */
	List<UserFavourProduct> deleteByfavourID(int favourID);

}
