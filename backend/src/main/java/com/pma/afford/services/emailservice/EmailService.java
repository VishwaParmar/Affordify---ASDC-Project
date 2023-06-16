package com.pma.afford.services.emailservice;
/**

 Interface for email service to send emails.
 */
public interface EmailService {
    /**

     Sends a simple email to the given email address.
     @param toEmail the recipient email address
     @param subject the subject of the email
     @param body the body of the email
     */
    public void  sendSimpleEmail(String toEmail,
                                 String subject,
                                 String body
    );
    /**

     Sends a favorite email to the user containing their favorite products.
     */
    public void sendFavoriteEmailToUser();

    public void sendSignupAlert(String email);

    public void sendSigninAlert(String email);




}
