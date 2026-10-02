package com.example.e_commerceWebsite.service;

import com.example.e_commerceWebsite.dto.AuthResponse;
import com.example.e_commerceWebsite.dto.LoginRequest;
import com.example.e_commerceWebsite.dto.RegisterRequest;

public interface AuthService {

    String register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
