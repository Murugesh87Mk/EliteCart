package com.example.e_commerceWebsite.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Homecontroller {
    @GetMapping("/")
    public String home() {
        return "Welcome to the E-Commerce Website!";
    }
}