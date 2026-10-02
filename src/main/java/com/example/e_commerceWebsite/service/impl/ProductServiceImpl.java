package com.example.e_commerceWebsite.service.impl;

import com.example.e_commerceWebsite.dto.ProductRequest;
import com.example.e_commerceWebsite.dto.ProductResponse;
import com.example.e_commerceWebsite.entity.Product;
import com.example.e_commerceWebsite.repository.ProductRepository;
import com.example.e_commerceWebsite.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Override
    public ProductResponse addProduct(ProductRequest request) {

        Product product = Product.builder()
                .productName(request.getProductName())
                .description(request.getDescription())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .imageUrl(request.getImageUrl())
                .category(request.getCategory())
                .build();

        repository.save(product);

        return map(product);
    }

    @Override
    public List<ProductResponse> getAllProducts() {

        return repository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public ProductResponse getProduct(Long id) {

        Product product = repository.findById(id).orElseThrow();

        return map(product);
    }

    @Override
    public ProductResponse updateProduct(Long id,
                                         ProductRequest request) {

        Product product = repository.findById(id).orElseThrow();

        product.setProductName(request.getProductName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setImageUrl(request.getImageUrl());
        product.setCategory(request.getCategory());

        repository.save(product);

        return map(product);
    }

    @Override
    public void deleteProduct(Long id) {

        repository.deleteById(id);
    }

    private ProductResponse map(Product product) {

        return ProductResponse.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .imageUrl(product.getImageUrl())
                .category(product.getCategory())
                .build();
    }
}
