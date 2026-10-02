package com.example.e_commerceWebsite.repository;

import com.example.e_commerceWebsite.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
