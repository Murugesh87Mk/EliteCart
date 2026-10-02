package com.example.e_commerceWebsite.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartResponse {

    private Long cartId;

    private Long userId;

    private Long productId;

    private String productName;

    private Double price;

    private Integer quantity;

}
