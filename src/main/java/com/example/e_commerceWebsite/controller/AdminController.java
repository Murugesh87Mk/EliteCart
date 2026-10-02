package com.example.e_commerceWebsite.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "Welcome Admin";
    }

    @GetMapping("/manage")
    public String manage() {
        return "Admin Management Panel";
    }
}
