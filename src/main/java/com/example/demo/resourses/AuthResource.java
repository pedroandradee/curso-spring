package com.example.demo.resourses;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.security.JWTUtil;
import com.example.demo.security.UserSS;
import com.example.demo.services.UserService;

@RestController
@RequestMapping(value="/auth")
public class AuthResource {
    
    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "/refresh/token", method = RequestMethod.POST)
    public ResponseEntity<Void> refreshToken(HttpServletResponse res) {
        UserSS user = UserService.authenticated();
        String token = jwtUtil.generateToken(user.getUsername());
        res.addHeader("Authorization", "Bearer " + token);
        return ResponseEntity.noContent().build();
    }
}
