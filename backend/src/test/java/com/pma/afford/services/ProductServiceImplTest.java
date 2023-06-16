package com.pma.afford.services;

import com.pma.afford.entities.Product;
import com.pma.afford.repositories.ProductRepository;
import com.pma.afford.services.productservice.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productServiceImpl;

    @Test
    void getProductByNameTest() {

        Product product1 = new Product("Prime Sliced Boneless Skinless Chicken Breasts Raised Without Antibiotics", "Walmart", 10, "https://i5.walmartimages.ca/images/Thumbnails/486/179/6000200486179.jpg", "meat", "https://www.walmart.ca/en/ip/prime-sliced-boneless-skinless-chicken-breasts-raised-without-antibiotics/6000200373725", "Chicken");
        Product product2 = new Product("Your Fresh Market Barbecue Roasted Seasoned Chicken", "walmart", 20, "https://i5.walmartimages.ca/images/Thumbnails/019/2_4/62891500192_4.jpg", "meat", "https://www.walmart.ca/en/ip/your-fresh-market-barbecue-roasted-seasoned-chicken/6000195377105", "skinless chicken");

        when(productRepository.findByProductNameContainingIgnoreCase(anyString())).thenReturn(Arrays.asList(product1, product2));

        // Call the method under test
        List<Product> actualResult = productServiceImpl.getProductByName("meat");

        // Verify the results
        assertEquals(2, actualResult.size());
        assertEquals(actualResult.get(0).getName(), product1.getName());
        assertEquals(actualResult.get(1).getName(), product2.getName());
    }

    @Test
    void getProductByNameTest_Empty() {

        Product product1 = new Product("Prime Sliced Boneless Skinless Chicken Breasts Raised Without Antibiotics", "Walmart", 10, "https://i5.walmartimages.ca/images/Thumbnails/486/179/6000200486179.jpg", "meat", "https://www.walmart.ca/en/ip/prime-sliced-boneless-skinless-chicken-breasts-raised-without-antibiotics/6000200373725", "Chicken");
        Product product2 = new Product("Your Fresh Market Barbecue Roasted Seasoned Chicken", "walmart", 20, "https://i5.walmartimages.ca/images/Thumbnails/019/2_4/62891500192_4.jpg", "meat", "https://www.walmart.ca/en/ip/your-fresh-market-barbecue-roasted-seasoned-chicken/6000195377105", "skinless chicken");

        when(productRepository.findByProductNameContainingIgnoreCase(anyString())).thenReturn(Arrays.asList(product1, product2));

        // Call the method under test
        List<Product> actualResult = productServiceImpl.getProductByName("");

        // Verify the results
        assertEquals(0, actualResult.size());
    }

    @Test
    void getProductByNameTest_Null() {

        Product product1 = new Product("Prime Sliced Boneless Skinless Chicken Breasts Raised Without Antibiotics", "Walmart", 10, "https://i5.walmartimages.ca/images/Thumbnails/486/179/6000200486179.jpg", "meat", "https://www.walmart.ca/en/ip/prime-sliced-boneless-skinless-chicken-breasts-raised-without-antibiotics/6000200373725", "Chicken");
        Product product2 = new Product("Your Fresh Market Barbecue Roasted Seasoned Chicken", "walmart", 20, "https://i5.walmartimages.ca/images/Thumbnails/019/2_4/62891500192_4.jpg", "meat", "https://www.walmart.ca/en/ip/your-fresh-market-barbecue-roasted-seasoned-chicken/6000195377105", "skinless chicken");

        when(productRepository.findByProductNameContainingIgnoreCase(anyString())).thenReturn(Arrays.asList(product1, product2));

        // Call the method under test
        List<Product> actualResult = productServiceImpl.getProductByName(null);

        // Verify the results
        assertEquals(0, actualResult.size());
    }

    @Test
    void getProductByCategoryTest() {

        Product product1 = new Product("Prime Sliced Boneless Skinless Chicken Breasts Raised Without Antibiotics", "Walmart", 10, "https://i5.walmartimages.ca/images/Thumbnails/486/179/6000200486179.jpg", "meat", "https://www.walmart.ca/en/ip/prime-sliced-boneless-skinless-chicken-breasts-raised-without-antibiotics/6000200373725", "Chicken");
        Product product2 = new Product("Your Fresh Market Barbecue Roasted Seasoned Chicken", "walmart", 20, "https://i5.walmartimages.ca/images/Thumbnails/019/2_4/62891500192_4.jpg", "meat", "https://www.walmart.ca/en/ip/your-fresh-market-barbecue-roasted-seasoned-chicken/6000195377105", "skinless chicken");

        when(productRepository.findByCategory(anyString())).thenReturn(Arrays.asList(product1, product2));

        // Call the method under test
        List<Product> actualResult = productServiceImpl.getProductByCategory("meat");

        // Verify the results
        assertEquals(2, actualResult.size());
        assertEquals(actualResult.get(0).getName(), product1.getName());
        assertEquals(actualResult.get(1).getName(), product2.getName());
    }

    @Test
    void getProductByCategoryTest_Empty() {

        Product product1 = new Product("Prime Sliced Boneless Skinless Chicken Breasts Raised Without Antibiotics", "Walmart", 10, "https://i5.walmartimages.ca/images/Thumbnails/486/179/6000200486179.jpg", "meat", "https://www.walmart.ca/en/ip/prime-sliced-boneless-skinless-chicken-breasts-raised-without-antibiotics/6000200373725", "Chicken");
        Product product2 = new Product("Your Fresh Market Barbecue Roasted Seasoned Chicken", "walmart", 20, "https://i5.walmartimages.ca/images/Thumbnails/019/2_4/62891500192_4.jpg", "meat", "https://www.walmart.ca/en/ip/your-fresh-market-barbecue-roasted-seasoned-chicken/6000195377105", "skinless chicken");

        when(productRepository.findByCategory(anyString())).thenReturn(Arrays.asList(product1, product2));

        // Call the method under test
        List<Product> actualResult = productServiceImpl.getProductByCategory("");

        // Verify the results
        assertEquals(0, actualResult.size());
    }

    @Test
    void getProductByCategoryTest_Null() {

        Product product1 = new Product("Prime Sliced Boneless Skinless Chicken Breasts Raised Without Antibiotics", "Walmart", 10, "https://i5.walmartimages.ca/images/Thumbnails/486/179/6000200486179.jpg", "meat", "https://www.walmart.ca/en/ip/prime-sliced-boneless-skinless-chicken-breasts-raised-without-antibiotics/6000200373725", "Chicken");
        Product product2 = new Product("Your Fresh Market Barbecue Roasted Seasoned Chicken", "walmart", 20, "https://i5.walmartimages.ca/images/Thumbnails/019/2_4/62891500192_4.jpg", "meat", "https://www.walmart.ca/en/ip/your-fresh-market-barbecue-roasted-seasoned-chicken/6000195377105", "skinless chicken");

        when(productRepository.findByCategory(anyString())).thenReturn(Arrays.asList(product1, product2));

        // Call the method under test
        List<Product> actualResult = productServiceImpl.getProductByCategory(null);

        // Verify the results
        assertEquals(0, actualResult.size());
    }


    @Test
    void getProductGreaterThan_positiveIntegers() {

        int productPrice = 1;
        String productName = "chicken";

        Product product1 = new Product("Prime Sliced Boneless Skinless Chicken Breasts Raised Without Antibiotics", "Walmart", 10, "https://i5.walmartimages.ca/images/Thumbnails/486/179/6000200486179.jpg", "meat", "https://www.walmart.ca/en/ip/prime-sliced-boneless-skinless-chicken-breasts-raised-without-antibiotics/6000200373725", "Chicken");
        Product product2 = new Product("Your Fresh Market Barbecue Roasted Seasoned Chicken", "walmart", 20, "https://i5.walmartimages.ca/images/Thumbnails/019/2_4/62891500192_4.jpg", "meat", "https://www.walmart.ca/en/ip/your-fresh-market-barbecue-roasted-seasoned-chicken/6000195377105", "skinless chicken");

        when(productRepository.getProductGreaterThan(productName, productPrice)).thenReturn(Arrays.asList(product1, product2));

        List<Product> actualResult = productServiceImpl.getProductGreaterThan(productName, productPrice);

        assertEquals(2, actualResult.size());
        assertEquals(actualResult.get(0).getName(), product1.getName());
        assertEquals(actualResult.get(1).getName(), product2.getName());
    }

    @Test
    void getProductGreaterThan_negativeIntegers() {

        int productPrice = -1;
        String productName = "chicken";

        Product product1 = new Product("Prime Sliced Boneless Skinless Chicken Breasts Raised Without Antibiotics", "Walmart", 10, "https://i5.walmartimages.ca/images/Thumbnails/486/179/6000200486179.jpg", "meat", "https://www.walmart.ca/en/ip/prime-sliced-boneless-skinless-chicken-breasts-raised-without-antibiotics/6000200373725", "Chicken");
        Product product2 = new Product("Your Fresh Market Barbecue Roasted Seasoned Chicken", "walmart", 20, "https://i5.walmartimages.ca/images/Thumbnails/019/2_4/62891500192_4.jpg", "meat", "https://www.walmart.ca/en/ip/your-fresh-market-barbecue-roasted-seasoned-chicken/6000195377105", "skinless chicken");

        when(productRepository.getProductGreaterThan(productName, productPrice)).thenReturn(Arrays.asList(product1, product2));

        List<Product> actualResult = productServiceImpl.getProductGreaterThan(productName, productPrice);

        assertEquals(0, actualResult.size());
    }

    @Test
    void getProductGreaterThan_Zero() {

        int productPrice = 0;
        String productName = "chicken";

        Product product1 = new Product("Prime Sliced Boneless Skinless Chicken Breasts Raised Without Antibiotics", "Walmart", 10, "https://i5.walmartimages.ca/images/Thumbnails/486/179/6000200486179.jpg", "meat", "https://www.walmart.ca/en/ip/prime-sliced-boneless-skinless-chicken-breasts-raised-without-antibiotics/6000200373725", "Chicken");
        Product product2 = new Product("Your Fresh Market Barbecue Roasted Seasoned Chicken", "walmart", 20, "https://i5.walmartimages.ca/images/Thumbnails/019/2_4/62891500192_4.jpg", "meat", "https://www.walmart.ca/en/ip/your-fresh-market-barbecue-roasted-seasoned-chicken/6000195377105", "skinless chicken");

        when(productRepository.getProductGreaterThan(productName, productPrice)).thenReturn(Arrays.asList(product1, product2));

        List<Product> actualResult = productServiceImpl.getProductGreaterThan(productName, productPrice);

        assertEquals(0, actualResult.size());
    }

    @Test
    void getProductLesserThan_positiveIntegers() {

        int productValue = 30;
        String productName = "chicken";

        Product product1 = new Product("Prime Sliced Boneless Skinless Chicken Breasts Raised Without Antibiotics", "Walmart", 10, "https://i5.walmartimages.ca/images/Thumbnails/486/179/6000200486179.jpg", "meat", "https://www.walmart.ca/en/ip/prime-sliced-boneless-skinless-chicken-breasts-raised-without-antibiotics/6000200373725", "Chicken");
        Product product2 = new Product("Your Fresh Market Barbecue Roasted Seasoned Chicken", "walmart", 20, "https://i5.walmartimages.ca/images/Thumbnails/019/2_4/62891500192_4.jpg", "meat", "https://www.walmart.ca/en/ip/your-fresh-market-barbecue-roasted-seasoned-chicken/6000195377105", "skinless chicken");

        when(productRepository.getProductLesserThan(productName, productValue)).thenReturn(Arrays.asList(product1, product2));

        List<Product> actualResult = productServiceImpl.getProductLesserThan(productName, productValue);

        assertEquals(2, actualResult.size());
        assertEquals(actualResult.get(0).getName(), product1.getName());
        assertEquals(actualResult.get(1).getName(), product2.getName());
    }

    @Test
    void getProductLesserThan_negativeIntegers() {

        int productValue = -1;
        String productName = "chicken";

        Product product1 = new Product("Prime Sliced Boneless Skinless Chicken Breasts Raised Without Antibiotics", "Walmart", 10, "https://i5.walmartimages.ca/images/Thumbnails/486/179/6000200486179.jpg", "meat", "https://www.walmart.ca/en/ip/prime-sliced-boneless-skinless-chicken-breasts-raised-without-antibiotics/6000200373725", "Chicken");
        Product product2 = new Product("Your Fresh Market Barbecue Roasted Seasoned Chicken", "walmart", 20, "https://i5.walmartimages.ca/images/Thumbnails/019/2_4/62891500192_4.jpg", "meat", "https://www.walmart.ca/en/ip/your-fresh-market-barbecue-roasted-seasoned-chicken/6000195377105", "skinless chicken");

        when(productRepository.getProductLesserThan(productName, productValue)).thenReturn(Arrays.asList(product1, product2));

        List<Product> actualResult = productServiceImpl.getProductLesserThan(productName, productValue);

        assertEquals(0, actualResult.size());
    }

    @Test
    void getProductLesserThan_Zero() {

        int productValue = 0;
        String productName = "chicken";

        Product product1 = new Product("Prime Sliced Boneless Skinless Chicken Breasts Raised Without Antibiotics", "Walmart", 10, "https://i5.walmartimages.ca/images/Thumbnails/486/179/6000200486179.jpg", "meat", "https://www.walmart.ca/en/ip/prime-sliced-boneless-skinless-chicken-breasts-raised-without-antibiotics/6000200373725", "Chicken");
        Product product2 = new Product("Your Fresh Market Barbecue Roasted Seasoned Chicken", "walmart", 20, "https://i5.walmartimages.ca/images/Thumbnails/019/2_4/62891500192_4.jpg", "meat", "https://www.walmart.ca/en/ip/your-fresh-market-barbecue-roasted-seasoned-chicken/6000195377105", "skinless chicken");

        when(productRepository.getProductLesserThan(productName, productValue)).thenReturn(Arrays.asList(product1, product2));

        List<Product> actualResult = productServiceImpl.getProductLesserThan(productName, productValue);

        assertEquals(0, actualResult.size());
    }
}