package com.example.e_commerceWebsite.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/super-admin")
public class SuperAdminController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "Welcome Super Admin";
    }

    @GetMapping("/reports")
    public String reports() {
        return "Super Admin Reports";
    }
}
