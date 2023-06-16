package com.pma.afford.services.productservice;


import com.pma.afford.entities.Product;
import com.pma.afford.entities.ProductDifferentDate;
import com.pma.afford.repositories.ProductDifferentRateRepository;
import com.pma.afford.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Service class for Product related operations.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductDifferentRateRepository productDifferentRateRepository;

    /**
     * Returns a list of Products containing the given name in their product name.
     *
     * @param productName the name to search for
     * @return a list of products matching the search criteria
     */

    @Override
    public List<Product> getProductByName(String productName) {
        List<Product> availableProducts = new ArrayList<>();
        if (productName != null && !productName.isEmpty()) {
            productRepository.findByProductNameContainingIgnoreCase(productName).forEach(availableProducts::add);
        }
        return availableProducts;
    }

    /**
     * Returns a list of Products that belong to the given category.
     *
     * @param categoryName the name of the category to search for
     * @return a list of products belonging to the given category
     */
    @Override
    public List<Product> getProductByCategory(String categoryName) {
        List<Product> product = new ArrayList<>();
        if (categoryName != null && !categoryName.isEmpty()) {
            productRepository.findByCategory(categoryName).forEach(product::add);
        }
        return product;
    }

    /**
     * Returns a list of Products whose price is greater than the given value and whose name contains the given string.
     *
     * @param productName  the name to search for
     * @param productPrice the minimum price of the products to search for
     * @return a list of products matching the search criteria
     */
    @Override
    public List<Product> getProductGreaterThan(String productName, int productPrice) {
        List<Product> product = new ArrayList<>();
        if (productPrice > 0) {
            productRepository.getProductGreaterThan(productName, productPrice).forEach(product::add);
        }
        return product;
    }

    /**
     * Returns a list of Products whose price is lesser than the given value and whose name contains the given string.
     *
     * @param productName  the name to search for
     * @param productValue the maximum price of the products to search for
     * @return a list of products matching the search criteria
     */
    @Override
    public List<Product> getProductLesserThan(String productName, int productValue) {
        List<Product> product = new ArrayList<>();
        if (productValue > 0) {
            productRepository.getProductLesserThan(productName, productValue).forEach(product::add);
        }
        return product;
    }

    /**
     * Returns a map of prices and dates for the given product ID.
     *
     * @param id the ID of the product to get the price chart for
     * @return a map of prices and dates for the given product ID
     */
    @Override
    public HashMap<String, List<String>> getProductPriceChartByID(String id) {
        HashMap<String, List<String>> chartdata = new HashMap<String, List<String>>();
        List<String> date = new ArrayList<>();
        List<String> price = new ArrayList<>();
        List<ProductDifferentDate> pList = productDifferentRateRepository.getpriceofproduct(id);

        for (int i = 0; i < pList.size(); i++) {
            date.add(pList.get(i).getDate());
            price.add(pList.get(i).getPrice());
        }
        chartdata.put("date", date);
        chartdata.put("price", price);
        return chartdata;
    }
}
