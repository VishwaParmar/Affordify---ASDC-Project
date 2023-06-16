package com.pma.afford.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.pma.afford.services.userservice.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;

import com.pma.afford.entities.User;
import com.pma.afford.repositories.UserRepository;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    JwtEncoder jwtEncoder;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    void testSaveNewUser_UserDoesNotExist() {

        // Arrange
        User user = new User("Affordify", "Group24", "Affordify@gmail.com", "password", 90108);

        when(userRepository.existsByUserMail(user.getUserMail())).thenReturn(false);
        when(passwordEncoder.encode(user.getUserPassword())).thenReturn("$2a$12$U4Mtwusn/Z8pN83rLaD4AOY3O9AogLZRkJFfhYjQK9lvS090ChFBK");
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        String result = userServiceImpl.saveNewUser(user);

        // Assert
        assertEquals("User Saved successfully", result);
    }

    @Test
    void testSaveNewUser_UserAlreadyExist() {

        // Arrange
        User user = new User("Affordify", "Group24", "Affordify@gmail.com", "password", 90108236);

        when(userRepository.existsByUserMail(user.getUserMail())).thenReturn(true);

        // Act
        String result = userServiceImpl.saveNewUser(user);

        // Assert
        assertEquals("User already exists", result);
    }

    @Test
    void findAllUser() {
        User userGiriSharan = new User("Affordify", "Group24", "Affordify@gmail.com", "password", 9010);
        User userKovarthanan = new User("Affordify", "Group24", "Affordify@gmail.com", "password2", 901025);
        List<User> availableUsers = Arrays.asList(userGiriSharan, userKovarthanan);


        Mockito.when(userRepository.findAll()).thenReturn(availableUsers);

        List<String> expectedUserList = Arrays.asList(userGiriSharan.getUserMail(), userKovarthanan.getUserMail());
        List<String> actualUserList = userServiceImpl.findAllUser();
        assertEquals(expectedUserList, actualUserList);
    }
}