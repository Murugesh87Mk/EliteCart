package com.example.e_commerceWebsite.controller;

import com.example.e_commerceWebsite.dto.AuthResponse;
import com.example.e_commerceWebsite.dto.LoginRequest;
import com.example.e_commerceWebsite.dto.RegisterRequest;
import com.example.e_commerceWebsite.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = {
    "http://localhost:5173",
    "http://localhost:5174",
    "http://localhost:5175",
    "http://localhost:5176"
})
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String register(
            @RequestBody RegisterRequest request) {

        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody LoginRequest request) {

        return authService.login(request);
    }
}
