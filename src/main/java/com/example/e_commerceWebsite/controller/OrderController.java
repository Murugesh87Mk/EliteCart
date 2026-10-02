package com.example.e_commerceWebsite.controller;

import com.example.e_commerceWebsite.dto.OrderRequest;
import com.example.e_commerceWebsite.dto.OrderResponse;
import com.example.e_commerceWebsite.entity.OrderStatus;
import com.example.e_commerceWebsite.service.OrderService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://localhost:5174",
        "http://localhost:5175",
        "http://localhost:5176"
})
public class OrderController {

    private final OrderService orderService;

    // Place Order
    @PostMapping
    public OrderResponse placeOrder(
            @RequestBody OrderRequest request) {

        return orderService.placeOrder(request);
    }

    // Get all orders of a user
    @GetMapping("/user/{userId}")
    public List<OrderResponse> getOrders(
            @PathVariable Long userId) {

        return orderService.getOrders(userId);
    }

    // Get order by id
    @GetMapping("/{orderId}")
    public OrderResponse getOrder(
            @PathVariable Long orderId) {

        return orderService.getOrder(orderId);
    }

    // Update order status
    @PutMapping("/{orderId}/status")
    public OrderResponse updateStatus(
            @PathVariable Long orderId,
            @RequestParam OrderStatus status) {

        return orderService.updateOrderStatus(orderId, status);
    }

}
