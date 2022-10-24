package com.example.demo.services;

// import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.example.demo.domain.Order;

public interface EmailService {
    
    void sendOrderConfirmationEmail(Order obj);

    void sendMail(SimpleMailMessage msg);

    // void sendOrderConfirmationHtmlEmail(Order obj);

    // void sendHtmlEmail(MimeMessage msg);
    
}
