package com.example.e_commerceWebsite.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "Welcome Customer";
    }

    @GetMapping("/orders")
    public String orders() {
        return "Customer Orders";
    }
}
