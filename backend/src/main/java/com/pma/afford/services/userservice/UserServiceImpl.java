package com.pma.afford.services.userservice;

import com.pma.afford.entities.User;
import com.pma.afford.repositories.UserRepository;
import com.pma.afford.services.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
/**

 This is the implementation class for UserService interface.
 It provides the implementation for saving new user, generating token and finding all users.
 */

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private JwtEncoder jwtEncoder;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;

	/**
	 * This method saves a new user to the database.
	 * @param user The User object to be saved
	 * @return A string message indicating whether the user was saved or already exists in the database
	 */

	@Override
	public String saveNewUser(User user) {

		if (!userRepository.existsByUserMail(user.getUserMail())) {
			user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
			userRepository.save(user);
			return "User Saved successfully";
		}

		return "User already exists";
	}
	/**
	 * This method generates a JWT token for the authenticated user.
	 * @param authentication The Authentication object for the authenticated user
	 * @return The JWT token generated for the user
	 */

	@Override
	public String generateToken(Authentication authentication) {
		 int TOKEN_EXPIRATION_TIME_IN_MINUTES = 10;

		Instant now = Instant.now();
		String scope = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).findFirst()
				.map(authority -> authority.isEmpty() ? "USER" : authority).orElse("USER");
		JwtClaimsSet claims = JwtClaimsSet.builder().issuer("girisharanreddy").issuedAt(now)
				.expiresAt(now.plus(TOKEN_EXPIRATION_TIME_IN_MINUTES, ChronoUnit.MINUTES)).subject(authentication.getName()).claim("scope", scope)
				.build();
		// JwsHeader jwsHeader = JwsHeader.with(() -> "HS256").build();
		return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	}

	/**
	 * This method finds all the users in the database.
	 * @return A List of strings containing the email address of all the users in the database.
	 */
	@Override
	public List<String> findAllUser() {
		List<String> user = new ArrayList<>();
		for(User i: userRepository.findAll()){

			user.add(i.getUserMail());
		}


				return user;
	}
}