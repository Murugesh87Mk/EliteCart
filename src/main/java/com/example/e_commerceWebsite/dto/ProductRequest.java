package com.example.e_commerceWebsite.dto;

import lombok.Data;

@Data
public class ProductRequest {

    private String productName;

    private String description;

    private Double price;

    private Integer quantity;

    private String imageUrl;

    private String category;
}