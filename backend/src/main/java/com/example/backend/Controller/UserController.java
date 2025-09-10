package com.example.backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.backend.Models.UserModel;
import com.example.backend.Repo.UserRepo;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;
    

    @PostMapping("/login")
    public UserModel login(@RequestBody UserModel body) {
        String username = body.getUsername();
        String password = body.getPassword();

        if(username == null || password == null) {
            return null;
        }

        userRepo.findAll().forEach(user -> {
            if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return;
            }
        });

        return null;
    }
    
    @PostMapping("/register")
    public String register(@RequestBody UserModel body) {
        body.setPassword(passwordEncoder.encode(body.getPassword()));
        userRepo.save(body);
        return "registered";
    }
    
}
