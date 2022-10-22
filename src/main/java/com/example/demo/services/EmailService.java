package com.example.demo.services;

import org.springframework.mail.SimpleMailMessage;

import com.example.demo.domain.Order;

public interface EmailService {
    
    void sendOrderConfirmationEmail(Order obj);

    void sendMail(SimpleMailMessage msg);
    
}
