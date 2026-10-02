package com.example.e_commerceWebsite.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {

    private Long id;

    private String productName;

    private String description;

    private Double price;

    private Integer quantity;

    private String imageUrl;

    private String category;
}
