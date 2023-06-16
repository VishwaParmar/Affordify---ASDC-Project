package com.pma.afford.controllers;

import com.pma.afford.services.emailservice.EmailService;
import com.pma.afford.services.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.pma.afford.entities.User;
/**

 This class represents the RESTful API for managing user authentication and authorization.
 It provides endpoints for user signup, user login, and a home page.
 The class is annotated with @RestController and @RequestMapping("/afford").
 It also allows cross-origin requests with @CrossOrigin(origins = "*").
 */
@RestController
@RequestMapping("/afford")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	EmailService emailService;

	/**
	 This endpoint allows a user to sign up with the system.
	 @param user The User object containing the user's information.
	 @return ResponseEntity containing the HTTP status and a message indicating success or failure.
	 */
	@PostMapping("/signup")
	public ResponseEntity<String> saveUser(@RequestBody User user) {
		emailService.sendSignupAlert(user.getUserMail());
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveNewUser(user));
	}

	/**

	 This endpoint allows a user to login to the system.
	 @param authentication The Authentication object containing the user's authentication information.
	 @return The generated token for the user's session.
	 */
	@GetMapping("/login")
	public String loginUser(Authentication authentication) {
		System.out.println(authentication.getName());
		String tokenGenerated = userService.generateToken(authentication);
		System.out.println("Generated Token:"+tokenGenerated);
		return tokenGenerated;
	}

	/**

	 This endpoint displays the home page for the user.
	 @return A string representing the name of the home page.
	 */
	@GetMapping("/home/{email}")
	public String homePage(@PathVariable("email") String email) {
		emailService.sendSigninAlert(email);
		return "home";

	}
}
