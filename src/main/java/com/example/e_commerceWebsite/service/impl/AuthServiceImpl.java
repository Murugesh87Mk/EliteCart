package com.example.e_commerceWebsite.service.impl;

import com.example.e_commerceWebsite.dto.AuthResponse;
import com.example.e_commerceWebsite.dto.LoginRequest;
import com.example.e_commerceWebsite.dto.RegisterRequest;
import com.example.e_commerceWebsite.entity.Role;
import com.example.e_commerceWebsite.entity.User;
import com.example.e_commerceWebsite.repository.UserRepository;
import com.example.e_commerceWebsite.security.JwtService;
import com.example.e_commerceWebsite.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public String register(RegisterRequest request) {
        if (request.getFullName() == null || request.getFullName().isBlank()
                || request.getEmail() == null || request.getEmail().isBlank()
                || request.getPassword() == null || request.getPassword().length() < 6) {
            throw new IllegalArgumentException("Name, email and a password of at least 6 characters are required");
        }

        String email = request.getEmail().trim().toLowerCase();
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = User.builder()
                .fullName(request.getFullName().trim())
                .email(email)
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CUSTOMER)
                .enabled(true)
                .build();

        userRepository.save(user);
        return "Customer Registered Successfully";
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        String email = request.getEmail() == null ? "" : request.getEmail().trim().toLowerCase();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        String token = jwtService.generateToken(user.getEmail(), user.getRole().name());
        return new AuthResponse(
                token,
                user.getRole().name(),
                user.getEmail(),
                user.getId(),
                user.getFullName());
    }
}
