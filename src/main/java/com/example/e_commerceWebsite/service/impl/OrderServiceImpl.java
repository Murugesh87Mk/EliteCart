package com.example.e_commerceWebsite.service.impl;

import com.example.e_commerceWebsite.dto.*;
import com.example.e_commerceWebsite.entity.*;
import com.example.e_commerceWebsite.repository.*;
import com.example.e_commerceWebsite.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    @Override
    @Transactional
    public OrderResponse placeOrder(OrderRequest request) {
        return createOrder(request);
    }

    private OrderResponse createOrder(OrderRequest request) {
        if (request.getItems() == null || request.getItems().isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item");
        }

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Order order = Order.builder()
                .user(user)
                .orderDate(LocalDateTime.now())
                .status(OrderStatus.CONFIRMED)
                .paymentStatus("COD / PENDING")
                .trackingNumber("ESHOP" + System.currentTimeMillis())
                .estimatedDelivery(LocalDateTime.now().plusDays(4))
                .totalAmount(0.0)
                .orderItems(new ArrayList<>())
                .build();

        double total = 0.0;

        for (OrderItemRequest itemRequest : request.getItems()) {
            if (itemRequest.getQuantity() == null || itemRequest.getQuantity() < 1) {
                throw new IllegalArgumentException("Invalid order quantity");
            }

            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));

            if (product.getQuantity() < itemRequest.getQuantity()) {
                throw new IllegalArgumentException(
                        "Insufficient stock for " + product.getProductName());
            }

            product.setQuantity(product.getQuantity() - itemRequest.getQuantity());

            OrderItem item = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(itemRequest.getQuantity())
                    .price(product.getPrice())
                    .build();

            order.getOrderItems().add(item);
            total += product.getPrice() * itemRequest.getQuantity();
        }

        order.setTotalAmount(total);
        Order saved = orderRepository.save(order);
        cartRepository.deleteByUserId(user.getId());

        return map(saved);
    }

    @Override
    public List<OrderResponse> getOrders(Long userId) {
        return orderRepository.findByUserId(userId).stream().map(this::map).toList();
    }

    @Override
    public OrderResponse getOrder(Long orderId) {
        return map(orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found")));
    }

    @Override
    public OrderResponse updateOrderStatus(Long orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        order.setStatus(status);
        return map(orderRepository.save(order));
    }

    private OrderResponse map(Order order) {
        List<OrderItemResponse> items = order.getOrderItems().stream()
                .map(item -> OrderItemResponse.builder()
                        .productId(item.getProduct().getId())
                        .productName(item.getProduct().getProductName())
                        .quantity(item.getQuantity())
                        .price(item.getPrice())
                        .totalPrice(item.getPrice() * item.getQuantity())
                        .build())
                .toList();

        return OrderResponse.builder()
                .orderId(order.getId())
                .userId(order.getUser().getId())
                .orderDate(order.getOrderDate())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .paymentStatus(order.getPaymentStatus())
                .trackingNumber(order.getTrackingNumber())
                .estimatedDelivery(order.getEstimatedDelivery())
                .items(items)
                .build();
    }
}
