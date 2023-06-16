package com.pma.afford.services;

import com.pma.afford.entities.Product;
import com.pma.afford.entities.UserFavourProduct;
import com.pma.afford.repositories.ProductRepository;
import com.pma.afford.repositories.UserProductRepository;
import com.pma.afford.services.userproductservice.UserProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class UserProductServiceImplTest {

    @InjectMocks
    UserProductServiceImpl userProductServiceImpl;
    @Mock
    private UserProductRepository userProductRepository;
    @Mock
    private ProductRepository productRepository;

    @Test
    void saveFavouriteProduct_NewFavourite() {

        // Arrange
        String userName = "giri.pusuluru@gmail.com", expectedResult = "saved";
        int productId = 1;
        UserFavourProduct userFavourProduct = new UserFavourProduct(userName, productId);
        when(userProductRepository.existsByUserMailAndProductID(userName, productId)).thenReturn(false);
        when(userProductRepository.save(any(UserFavourProduct.class))).thenReturn(userFavourProduct);

        // Act
        String actualResult = userProductServiceImpl.saveFaovouriteProduct(userFavourProduct);

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void saveFavouriteProduct_AlreadyFavourite() {

        String userMail = "giri.pusuluru@gmail.com", expectedResult = "Product is already your favourite";
        int productId = 1;
        // Arrange
        UserFavourProduct userFavourProduct = new UserFavourProduct(userMail, productId);
        when(userProductRepository.existsByUserMailAndProductID(userFavourProduct.getUserMail(), userFavourProduct.getProductID())).thenReturn(true);

        // Act
        String actualResult = userProductServiceImpl.saveFaovouriteProduct(userFavourProduct);

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void saveFavouriteProduct_UserMailNull() {

        String userMail = null, expectedResult = "kindly check once";
        int productId = 1;
        // Arrange
        UserFavourProduct userFavourProduct = new UserFavourProduct(userMail, productId);
        when(userProductRepository.existsByUserMailAndProductID(userFavourProduct.getUserMail(), userFavourProduct.getProductID())).thenReturn(true);

        // Act
        String actualResult = userProductServiceImpl.saveFaovouriteProduct(userFavourProduct);

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void saveFavouriteProduct_UserMailEmpty() {

        String userMail = "", expectedResult = "kindly check once";
        int productId = 1;
        // Arrange
        UserFavourProduct userFavourProduct = new UserFavourProduct(userMail, productId);
        when(userProductRepository.existsByUserMailAndProductID(userFavourProduct.getUserMail(), userFavourProduct.getProductID())).thenReturn(true);

        // Act
        String actualResult = userProductServiceImpl.saveFaovouriteProduct(userFavourProduct);

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void saveFavouriteProduct_ProductIdNegative() {

        String userMail = "giri.pusuluru@gmail.com", expectedResult = "kindly check once";
        int productId = -1;
        // Arrange
        UserFavourProduct userFavourProduct = new UserFavourProduct(userMail, productId);
        when(userProductRepository.existsByUserMailAndProductID(userFavourProduct.getUserMail(), userFavourProduct.getProductID())).thenReturn(true);

        // Act
        String actualResult = userProductServiceImpl.saveFaovouriteProduct(userFavourProduct);

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void findFavourProduct() {

        // Arrange
        String userMail = "giri.pusuluru@gmail.com";

        UserFavourProduct userFavourProduct1 = new UserFavourProduct(userMail, 60520);
        UserFavourProduct userFavourProduct2 = new UserFavourProduct(userMail, 69537);
        List<UserFavourProduct> userFavourProducts = Arrays.asList(userFavourProduct1, userFavourProduct2);

        Product product1 = new Product("Prime Sliced Boneless Skinless Chicken Breasts Raised Without Antibiotics", "Walmart", 10, "https://i5.walmartimages.ca/images/Thumbnails/486/179/6000200486179.jpg", "meat", "https://www.walmart.ca/en/ip/prime-sliced-boneless-skinless-chicken-breasts-raised-without-antibiotics/6000200373725", "Chicken");
        Product product2 = new Product("Your Fresh Market Barbecue Roasted Seasoned Chicken", "walmart", 20, "https://i5.walmartimages.ca/images/Thumbnails/019/2_4/62891500192_4.jpg", "meat", "https://www.walmart.ca/en/ip/your-fresh-market-barbecue-roasted-seasoned-chicken/6000195377105", "skinless chicken");
        List<Product> productList = Arrays.asList(product1, product2);

        // Mock for loop
        Mockito.when(userProductRepository.findByUserMail(userMail)).thenReturn(userFavourProducts);
        Mockito.when(productRepository.findById(60520)).thenReturn(Optional.of(product1));
        Mockito.when(productRepository.findById(69537)).thenReturn(Optional.of(product2));

        // Act
        List<Product> favourProducts = userProductServiceImpl.findFavourProducts(userMail);

        // Assert
        assertEquals(2, favourProducts.size());
        assertEquals(product1, favourProducts.get(0));
        assertEquals(product2, favourProducts.get(1));
    }

    @Test
    void findFavourProduct_UserMailEmpty() {

        // Arrange
        String userMail = "";

        UserFavourProduct userFavourProduct1 = new UserFavourProduct(userMail, 60520);
        UserFavourProduct userFavourProduct2 = new UserFavourProduct(userMail, 69537);
        List<UserFavourProduct> userFavourProducts = Arrays.asList(userFavourProduct1, userFavourProduct2);

        Product product1 = new Product("Prime Sliced Boneless Skinless Chicken Breasts Raised Without Antibiotics", "Walmart", 10, "https://i5.walmartimages.ca/images/Thumbnails/486/179/6000200486179.jpg", "meat", "https://www.walmart.ca/en/ip/prime-sliced-boneless-skinless-chicken-breasts-raised-without-antibiotics/6000200373725", "Chicken");
        Product product2 = new Product("Your Fresh Market Barbecue Roasted Seasoned Chicken", "walmart", 20, "https://i5.walmartimages.ca/images/Thumbnails/019/2_4/62891500192_4.jpg", "meat", "https://www.walmart.ca/en/ip/your-fresh-market-barbecue-roasted-seasoned-chicken/6000195377105", "skinless chicken");
        List<Product> productList = Arrays.asList(product1, product2);

        // Mock for loop
        Mockito.when(userProductRepository.findByUserMail(userMail)).thenReturn(userFavourProducts);
        Mockito.when(productRepository.findById(60520)).thenReturn(Optional.of(product1));
        Mockito.when(productRepository.findById(69537)).thenReturn(Optional.of(product2));

        // Act
        List<Product> favourProducts = userProductServiceImpl.findFavourProducts(userMail);

        // Assert
        assertEquals(0, favourProducts.size());
    }

    @Test
    void findFavourProduct_UserMailNull() {

        // Arrange
        String userMail = null;

        UserFavourProduct userFavourProduct1 = new UserFavourProduct(userMail, 60520);
        UserFavourProduct userFavourProduct2 = new UserFavourProduct(userMail, 69537);
        List<UserFavourProduct> userFavourProducts = Arrays.asList(userFavourProduct1, userFavourProduct2);

        Product product1 = new Product("Prime Sliced Boneless Skinless Chicken Breasts Raised Without Antibiotics", "Walmart", 10, "https://i5.walmartimages.ca/images/Thumbnails/486/179/6000200486179.jpg", "meat", "https://www.walmart.ca/en/ip/prime-sliced-boneless-skinless-chicken-breasts-raised-without-antibiotics/6000200373725", "Chicken");
        Product product2 = new Product("Your Fresh Market Barbecue Roasted Seasoned Chicken", "walmart", 20, "https://i5.walmartimages.ca/images/Thumbnails/019/2_4/62891500192_4.jpg", "meat", "https://www.walmart.ca/en/ip/your-fresh-market-barbecue-roasted-seasoned-chicken/6000195377105", "skinless chicken");
        List<Product> productList = Arrays.asList(product1, product2);

        // Mock for loop
        Mockito.when(userProductRepository.findByUserMail(userMail)).thenReturn(userFavourProducts);
        Mockito.when(productRepository.findById(60520)).thenReturn(Optional.of(product1));
        Mockito.when(productRepository.findById(69537)).thenReturn(Optional.of(product2));

        // Act
        List<Product> favourProducts = userProductServiceImpl.findFavourProducts(userMail);

        // Assert
        assertEquals(0, favourProducts.size());
    }
}