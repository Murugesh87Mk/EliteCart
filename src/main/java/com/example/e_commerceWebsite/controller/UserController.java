package com.example.e_commerceWebsite.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.e_commerceWebsite.dto.UserResponse;
import com.example.e_commerceWebsite.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponse getUser(
            @PathVariable Long id) {

        return userService.getUser(id);
    }
}
