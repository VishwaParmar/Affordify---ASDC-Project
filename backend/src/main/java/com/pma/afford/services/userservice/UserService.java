package com.pma.afford.services.userservice;

import com.pma.afford.entities.User;
import org.springframework.security.core.Authentication;

import java.util.List;
/**

 Interface for user related operations
 */
public interface UserService {
    /**

     Saves new user
     @param user user details to be saved
     @return success message
     */
    String saveNewUser(User user);
    /**

     Generates authentication token for a user
     @param authentication user authentication details
     @return generated token
     */
    String generateToken(Authentication authentication);
    /**

     Finds all the registered users
     @return list of all users
     */
    List<String> findAllUser();
}
