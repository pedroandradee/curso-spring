package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Client;
import com.example.demo.repositories.ClientRepository;
import com.example.demo.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClientRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client cl = repo.findByEmail(email);
        if (cl == null) {
            throw new UsernameNotFoundException(email);
        }

        return new UserSS(cl.getId(), cl.getEmail(), cl.getPassword(), cl.getUserTypes());
    }
    
}
