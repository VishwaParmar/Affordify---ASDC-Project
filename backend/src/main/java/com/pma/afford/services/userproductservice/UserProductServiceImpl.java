package com.pma.afford.services.userproductservice;

import com.pma.afford.entities.Product;
import com.pma.afford.entities.ProductDifferentDate;
import com.pma.afford.entities.UserFavourProduct;
import com.pma.afford.repositories.ProductDifferentRateRepository;
import com.pma.afford.repositories.ProductRepository;
import com.pma.afford.repositories.UserProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class is the implementation of the UserProductService interface.
 * It provides methods for saving favourite products, finding favourite products of a user,
 * deleting a favourite product by its id, and getting products with price changes in the last two days.
 */
@Service
public class UserProductServiceImpl implements UserProductService {

    @Autowired
    ProductDifferentRateRepository productDifferentRateRepository;
    @Autowired
    private UserProductRepository userProductRepo;
    @Autowired
    private ProductRepository productRepository;

    /**
     * Saves a new favourite product for a user
     *
     * @param userFavourProduct UserFavourProduct object containing the user's email and the product ID
     * @return String "saved" if the product is saved successfully, "Product is already your favourite" if the product is already a favourite
     */
    @Override
    public String saveFaovouriteProduct(UserFavourProduct userFavourProduct) {
        if (userFavourProduct.getUserMail() != null && !userFavourProduct.getUserMail().isEmpty() && userFavourProduct.getProductID() >= 0) {
            if (!userProductRepo.existsByUserMailAndProductID(userFavourProduct.getUserMail(),
                    userFavourProduct.getProductID())) {
                userProductRepo.save(userFavourProduct);
                return "saved";
            }
            return "Product is already your favourite";
        }
        return "kindly check once";
    }

    /**
     * Finds the favourite products of a user
     *
     * @param userMail String containing the user's email
     * @return List of Product objects containing the user's favourite products
     */
    @Override
    public List<Product> findFavourProducts(String userMail) {
        List<Product> out = new ArrayList();
        if (userMail != null && !userMail.isEmpty()) {
            userProductRepo.findByUserMail(userMail).forEach(
                    f -> out.add(productRepository.findById(f.getProductID()).get()));
        }
        return out;
    }

    /**
     * Deletes a favourite product of a user by its ID
     *
     * @param productID Integer containing the product ID of the favourite product to delete
     * @param userMail  String containing the user's email
     * @return List of UserFavourProduct objects containing the deleted favourite product
     */
    @Override
    public List<UserFavourProduct> deleteFavoriteById(int productID, String userMail) {

        int favorid = -1;
        List<UserFavourProduct> foundProducts = userProductRepo.findByfavourID(productID, userMail);
        System.out.println(foundProducts.get(0).getFavourID());
        favorid = foundProducts.get(0).getFavourID();
        if (favorid != -1) {
            userProductRepo.deleteByfavourID(favorid);
        }
        return foundProducts;
    }

    /**
     * Gets products with price changes in the last two days for a user
     *
     * @param email String containing the user's email
     * @return List of Product objects containing the user's favourite products with price changes in the last two days
     */
    @Override
    public List<Product> getProductByDateDifference(String email) {
        HashMap<String, List<String>> chartdata = new HashMap<String, List<String>>();
        List<String> date = new ArrayList<>();
        List<String> price = new ArrayList<>();
        List<UserFavourProduct> favorite = userProductRepo.findByUserMail(email);
        List<Product> changedFavorite = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        // Create a formatter for the desired date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        // Format the date using the formatter
        String formattedDate = currentDate.format(formatter);
        LocalDate yesterdayDate = currentDate.minusDays(1);
        String formattedYesterdayDate = yesterdayDate.format(formatter);

        for (UserFavourProduct i : favorite) {
            String yesterdayPrice = "";
            String todayPrice = "";
            List<ProductDifferentDate> todaypricedata = productDifferentRateRepository.getproduct(String.valueOf(i.getProductID()), formattedDate);
            for (ProductDifferentDate pricedata : todaypricedata) {
                yesterdayPrice = pricedata.getPrice();
            }
            List<ProductDifferentDate> yesterdaypricedata = productDifferentRateRepository.getproduct(String.valueOf(i.getProductID()), formattedYesterdayDate);
            for (ProductDifferentDate pricedata : yesterdaypricedata) {
                todayPrice = pricedata.getPrice();
            }
            if (todayPrice != "" && yesterdayPrice != "") {
                if (Integer.parseInt(todayPrice) != Integer.parseInt(yesterdayPrice)) {
                    int productId = i.getProductID();
                    Product c = productRepository.findById(productId).orElse(null);
                    changedFavorite.add(c);
                }
            }
        }
        return changedFavorite;
    }

}
