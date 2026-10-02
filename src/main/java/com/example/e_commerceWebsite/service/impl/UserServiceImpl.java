package com.example.e_commerceWebsite.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.e_commerceWebsite.dto.UserResponse;
import com.example.e_commerceWebsite.entity.User;
import com.example.e_commerceWebsite.repository.UserRepository;
import com.example.e_commerceWebsite.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public List<UserResponse> getAllUsers() {

        return repository.findAll()
                .stream()
                .map(this::convert)
                .toList();
    }

    @Override
    public UserResponse getUser(Long id) {

        User user =
                repository.findById(id)
                .orElseThrow();

        return convert(user);
    }

    private UserResponse convert(User user) {

        return UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
