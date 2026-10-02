package com.example.e_commerceWebsite.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/intermediate")
public class IntermediateController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "Welcome Intermediate User";
    }

    @GetMapping("/products")
    public String products() {
        return "Manage Assigned Products";
    }
}
