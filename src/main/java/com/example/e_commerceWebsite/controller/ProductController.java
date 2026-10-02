package com.example.e_commerceWebsite.controller;

import com.example.e_commerceWebsite.dto.ProductRequest;
import com.example.e_commerceWebsite.dto.ProductResponse;
import com.example.e_commerceWebsite.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://localhost:5174",
        "http://localhost:5175",
        "http://localhost:5176"
})
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ProductResponse addProduct(
            @RequestBody ProductRequest request) {

        return service.addProduct(request);
    }

    @GetMapping
    public List<ProductResponse> getProducts() {

        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponse getProduct(
            @PathVariable Long id) {

        return service.getProduct(id);
    }

    @PutMapping("/{id}")
    public ProductResponse updateProduct(
            @PathVariable Long id,
            @RequestBody ProductRequest request) {

        return service.updateProduct(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(
            @PathVariable Long id) {

        service.deleteProduct(id);

        return "Product Deleted Successfully";
    }
}
