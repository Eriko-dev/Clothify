package com.example.clothify.controller;

import com.example.clothify.dto.LoginRequest;
import com.example.clothify.dto.LoginResponse;
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
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

}