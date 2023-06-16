package com.pma.afford.services.userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pma.afford.entities.User;
import com.pma.afford.repositories.UserRepository;

/**

 This class implements the UserDetailsService interface which provides a method to load user-specific data
 for authentication and authorization purposes.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	/**

	 This method loads user-specific data from the user repository using the provided username.
	 @param username the username of the user to load.
	 @return the UserDetails object for the provided user.
	 @throws UsernameNotFoundException if the provided username is not found in the user repository.
	 */
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		final User customer = userRepo.findByUserMail(username);
		
		if(customer == null) {
			throw new UsernameNotFoundException(username);
		}
		
		UserDetails user = org.springframework.security.core.userdetails.User.withUsername(customer.getUserMail()) // checking for username
								.password(customer.getUserPassword()) // checking whether password
								.authorities("USER") //giving the role as user by default
								.build();
		
		return user;
	}
}
