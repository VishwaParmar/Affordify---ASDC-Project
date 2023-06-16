package com.pma.afford.services.emailservice;

import com.pma.afford.entities.Product;
import com.pma.afford.services.userproductservice.UserProductService;
import com.pma.afford.services.userservice.UserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;
/**

 This class implements the EmailService interface and provides functionality to send emails using JavaMailSender.
 */
@Service
public class EmailServiceImp implements EmailService {
    /**

     JavaMailSender instance that will be used to send emails.
     */
    @Autowired
    private JavaMailSender emailSender;

    /**

     UserService instance to get user details.
     */

    @Autowired
    private UserService userService;
    @Autowired
    private UserProductService userProductService;

    /**

     This method sends a simple email with the given parameters.
     @param toEmail The email address of the recipient.
     @param subject The subject of the email.
     @param body The body of the email.
     */
    public void sendSimpleEmail(String toEmail, String subject, String body)
    {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("myproject.made@gmail.com");
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(body, true);
            emailSender.send(message);
        }
        catch (MessagingException e) {
            throw new RuntimeException(e);
        }


    }

    /**

     This method creates an email body with the given list of changed products and sends an email to the user.
     */
    @Override
    public void sendFavoriteEmailToUser()  {
        List<String> users = userService.findAllUser();

        for(String user:users){
            List<Product> changedFavorite = userProductService.getProductByDateDifference(user);
            if (!changedFavorite.isEmpty()) {
                createEmailBody(changedFavorite,user);
            }
        }
    }
    /**

     This method creates an email body with the given list of changed products and sends an email to the user.
     @param changedFavorite The list of changed products.
     */
    public void createEmailBody(List<Product> changedFavorite,String user){

        String emailbody = "<h1 style = 'margin-left: 390px;  background-color: red; width:42.5%;'>Checkout!! Price Change In Your Favorite Products</h1> </br>";
        for (Product changesProduct: changedFavorite) {
            emailbody += "<h1 style = 'margin-left: 553px; ' >"+changesProduct.getRealName()+"</h1>"
                    +"</br>"
                    +" <img style= 'width: 280px; margin-left: 554px;' src='"+changesProduct.getLink()+"'>"
                    +"</br>"
                    +"<p style='margin-left: 650px'>Vendor:" + changesProduct.getShopName()+"</p>"
                    +"</br>"
                    +"<p style='margin-left: 663px'>Price:" + changesProduct.getPrice()+"</p>"
                    +"</br>"
                    +" <a style='margin-left: 652px' href='"+changesProduct.getProductLink()+"'>ProductLink</a>"
                    +"</br>";
        }
        sendSimpleEmail(user,
                "Price Alert!!",emailbody);
    }
    @Override
    public void sendSignupAlert(String email) {
        sendSimpleEmail(email,
                "Welcome to Affordify"," We wanted to take a moment to personally thank you for signing up with Affordify. We are excited to have you on board and can't wait for you to start exploring the world of web scraping with our platform."
        );
    }

    @Override
    public void sendSigninAlert(String email) {
        sendSimpleEmail(email,
                "Sign In Alert","Dear user, you were signed in the Affrodify website moments ago."
        );
    }

}
