package com.example.e_commerceWebsite.repository;

import com.example.e_commerceWebsite.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
