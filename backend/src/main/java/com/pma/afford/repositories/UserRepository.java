package com.pma.afford.repositories;


import org.springframework.data.repository.CrudRepository;

import com.pma.afford.entities.User;
	/**
	 This interface represents the repository for managing User entities in the database.
		*/
public interface UserRepository extends CrudRepository<User, Long> {
		/**

		 Returns the User entity that matches the specified username.
		 @param username the email address of the user
		 @return the User entity that matches the specified username
		 */
	User findByUserMail(String username);

	  /**

		Checks whether a User entity exists in the database with the specified email address.
		@param userMail the email address to check
		@return true if a User entity exists with the specified email address, false otherwise
		*/
	Boolean existsByUserMail(String userMail);
}
