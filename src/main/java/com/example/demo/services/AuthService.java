package com.example.demo.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Client;
import com.example.demo.repositories.ClientRepository;
import com.example.demo.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {
    
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BCryptPasswordEncoder bcrypt;

    @Autowired
    private EmailService emailService;

    private Random rand = new Random();

    public void sendNewPassword(String email) {
        Client cl = clientRepository.findByEmail(email);
        if (cl == null) {
            throw new ObjectNotFoundException("Email não encontrado!");
        }

        String newPass = newPassword();
        cl.setPassword(bcrypt.encode(newPass));
        clientRepository.save(cl);
        emailService.sendNewPassword(cl, newPass);
    }

    private String newPassword() {
        char[] vet = new char[10];
        for (int i=0;i<10;i++) {
            vet[i] = randomChar();
        }

        return new String(vet);
    }

    private char randomChar() {
        int opt = rand.nextInt(3);
        //generates digitis 0~9
        if (opt == 0) {
            return (char) (rand.nextInt(10) + 48);
        } else if (opt == 1) {
            // generates a uppercase letter
            return (char) (rand.nextInt(26) + 65);
        } else {
            // generates a lowercase letter
            return (char) (rand.nextInt(26) + 97);
        }
    }

}
