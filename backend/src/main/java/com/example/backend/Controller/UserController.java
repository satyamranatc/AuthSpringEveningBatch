package com.example.backend.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.Config.JwtUtil;
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

    @Autowired
    JwtUtil jwtUtil;
    

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
    public Map register(@RequestBody UserModel body) {
        body.setPassword(passwordEncoder.encode(body.getPassword()));

        HashMap UserData = new HashMap<>();

        UserData.put("user", userRepo.save(body));
        UserData.put("token", jwtUtil.generateToken(body.getUsername()));

        return UserData;
    }
    
}
