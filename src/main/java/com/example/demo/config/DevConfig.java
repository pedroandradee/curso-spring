package com.example.demo.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.demo.services.DBService;
import com.example.demo.services.EmailService;
import com.example.demo.services.SMTPEmailService;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        if (!strategy.equals("create")) {
            return false;
        }
        dbService.instantiateDatabase();
        return true;
    }

    @Bean
    public EmailService emailService() {
        return new SMTPEmailService();
    }

}
