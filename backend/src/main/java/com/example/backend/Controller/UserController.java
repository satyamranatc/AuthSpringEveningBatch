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
    public Map<String, Object> login(@RequestBody UserModel body) {
        String username = body.getUsername();
        String password = body.getPassword();

        UserModel user = userRepo.findByUsername(username);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            String token = jwtUtil.generateToken(username);
            Map<String, Object> response = new HashMap<>();
            response.put("userData", user);
            response.put("token", token);
            return response;
        }

        throw new RuntimeException("Invalid username or password");
    }

    @PostMapping("/register")
    public Map register(@RequestBody UserModel body) {

        body.setPassword(passwordEncoder.encode(body.getPassword()));

        HashMap UserData = new HashMap<>();

        UserData.put("userData", userRepo.save(body));
        UserData.put("token", jwtUtil.generateToken(body.getUsername()));

        return UserData;
    }

}
