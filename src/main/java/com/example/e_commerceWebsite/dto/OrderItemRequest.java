package com.example.e_commerceWebsite.dto;

import lombok.Data;

@Data
public class OrderItemRequest {

    private Long productId;

    private Integer quantity;

}
