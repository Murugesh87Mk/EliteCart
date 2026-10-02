package com.example.e_commerceWebsite.dto;

import com.example.e_commerceWebsite.entity.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {

    private Long orderId;

    private Long userId;

    private LocalDateTime orderDate;

    private Double totalAmount;

    private OrderStatus status;

    private String paymentStatus;
    private String trackingNumber;
    private LocalDateTime estimatedDelivery;

    private List<OrderItemResponse> items;

}
