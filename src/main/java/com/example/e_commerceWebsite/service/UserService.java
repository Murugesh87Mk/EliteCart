package com.example.e_commerceWebsite.service;

import java.util.List;

import com.example.e_commerceWebsite.dto.UserResponse;

public interface UserService {

    List<UserResponse> getAllUsers();

    UserResponse getUser(Long id);
}
