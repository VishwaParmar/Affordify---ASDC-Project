package com.pma.afford.controllers;

import com.pma.afford.services.emailservice.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**

 The EmailController class is responsible for handling HTTP requests related to sending emails.
 It is annotated with @RestController and @RequestMapping to map the class to the "/sendemail" endpoint in the application.
 The @CrossOrigin annotation allows cross-origin requests from any origin.
 This class has a dependency on the EmailService class, which is injected using the @Autowired annotation.
 The method sendFavoriteEmail() is mapped to the "/sendemail/sendfavoriteemail" endpoint using the @GetMapping annotation.
 This method invokes the sendFavoriteEmailToUser() method of the EmailService instance to send an email containing the user's favorite items.
 */
@RestController
@RequestMapping("/sendemail")
@CrossOrigin(origins = "*")
public class EmailController {

    @Autowired
    EmailService emailService;

    @GetMapping("/sendfavoriteemail")

    public void sendFavoriteEmail(){
        emailService.sendFavoriteEmailToUser();
    }

}

