package com.pma.afford.services.userproductservice;

import java.util.List;

import com.pma.afford.entities.Product;
import com.pma.afford.entities.UserFavourProduct;
import org.springframework.transaction.annotation.Transactional;
/**

 This interface provides the methods to manage user's favorite products.
 */
public interface UserProductService {
	/**

	 Saves a new favorite product for a user.
	 @param userFavourProduct the favorite product to be saved
	 @return a message indicating whether the save operation was successful
	 */
	String saveFaovouriteProduct(UserFavourProduct userFavourProduct);
	/**

	 Retrieves a list of a user's favorite products.
	 @param userMail the email address of the user
	 @return a list of the user's favorite products
	 */
	List<Product> findFavourProducts(String userMail);
	/**

	 Deletes a user's favorite product by its ID.
	 @param productID the ID of the favorite product to be deleted
	 @param email the email address of the user
	 @return a list of the user's remaining favorite products after the deletion
	 */
	@Transactional
	List<UserFavourProduct> deleteFavoriteById(int  productID, String email);

	/**

	 Retrieves a list of products that have price changes from yesterday to today, for a given user.
	 @param email the email address of the user
	 @return a list of the products with price changes
	 */
	public List<Product> getProductByDateDifference(String email);
}
