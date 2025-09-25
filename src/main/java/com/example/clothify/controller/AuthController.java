package com.example.clothify.controller;

import com.example.clothify.entity.User;
import com.example.clothify.security.JwtService;
import com.example.clothify.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return authService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        // Lấy user từ database
        User loginUser = authService.login(user.getEmail(), user.getPassword())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        // Trả token JWT
        return jwtService.generateToken(loginUser.getEmail());
    }
}
