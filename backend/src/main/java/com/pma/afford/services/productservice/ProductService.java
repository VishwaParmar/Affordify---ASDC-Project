package com.pma.afford.services.productservice;

import com.pma.afford.entities.Product;
import com.pma.afford.entities.ProductDifferentDate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**

 ProductService is an interface that defines the methods to get the product information from a data source.
 */
public interface ProductService {
    public List<Product> getProductByName(String name);
    public List<Product> getProductByCategory(String name);
    public List<Product> getProductGreaterThan(String name,int value);
    public List<Product> getProductLesserThan(String name,int value);
    public HashMap<String,List<String>> getProductPriceChartByID(String id);

}
