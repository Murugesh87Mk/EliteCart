package com.example.e_commerceWebsite.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WishlistResponse {

    private Long wishlistId;

    private Long userId;

    private Long productId;

    private String productName;

    private Double price;

    private String imageUrl;

}
