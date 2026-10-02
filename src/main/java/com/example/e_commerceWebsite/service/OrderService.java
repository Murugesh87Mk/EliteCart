package com.example.e_commerceWebsite.service;

import com.example.e_commerceWebsite.dto.OrderRequest;
import com.example.e_commerceWebsite.dto.OrderResponse;
import com.example.e_commerceWebsite.entity.OrderStatus;

import java.util.List;

public interface OrderService {

    OrderResponse placeOrder(OrderRequest request);


    List<OrderResponse> getOrders(Long userId);

    OrderResponse getOrder(Long orderId);

    OrderResponse updateOrderStatus(Long orderId,
                                    OrderStatus status);

}
