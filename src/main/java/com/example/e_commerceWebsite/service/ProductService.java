package com.example.e_commerceWebsite.service;

import com.example.e_commerceWebsite.dto.ProductRequest;
import com.example.e_commerceWebsite.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse addProduct(ProductRequest request);

    List<ProductResponse> getAllProducts();

    ProductResponse getProduct(Long id);

    ProductResponse updateProduct(Long id, ProductRequest request);

    void deleteProduct(Long id);
}
