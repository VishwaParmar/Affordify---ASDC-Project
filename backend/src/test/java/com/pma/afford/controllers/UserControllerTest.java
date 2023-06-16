package com.pma.afford.controllers;

import com.pma.afford.entities.User;
import com.pma.afford.services.emailservice.EmailService;
import com.pma.afford.services.userservice.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class UserControllerTest {

    @Mock
    UserService userService;



    @Mock
    private EmailService emailService;

    @InjectMocks
    UserController userController;

    @Test
    void saveUserTest_NewUser() {

        User user = new User("Giri Sharan Reddy", "Pusuluru", "giri.pusuluru@gmail.com", "KannaReddy", 90101832);
        when(userService.saveNewUser(Mockito.any(User.class))).thenReturn("User saved successfully");

        ResponseEntity<String> result = userController.saveUser(user);
        verify(emailService).sendSignupAlert(user.getUserMail());
        verify(userService, times(1)).saveNewUser(user);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals("User saved successfully", result.getBody());
    }

    @Test
    void saveUserTest_ExistingUser() {

        User user = new User("Giri Sharan Reddy", "Pusuluru", "giri.pusuluru@gmail.com", "KannaReddy", 90101832);
        when(userService.saveNewUser(Mockito.any(User.class))).thenReturn("User already exists");

        ResponseEntity<String> result = userController.saveUser(user);
        verify(emailService).sendSignupAlert(user.getUserMail());
        verify(userService, times(1)).saveNewUser(user);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals("User already exists", result.getBody());
    }

    @Test
    void loginUser() {
        String expectedResult = "girisharanreddyToken";
        String testUserMail = "giri.pusuluru@gmail.com";
        String testUserPassword = "girisharanreddy";
        Authentication authentication = new UsernamePasswordAuthenticationToken(testUserMail, testUserPassword);

        when(userService.generateToken(authentication)).thenReturn(expectedResult);

        String actualResult = userService.generateToken(authentication);

        assertEquals(expectedResult, actualResult);
    }
}