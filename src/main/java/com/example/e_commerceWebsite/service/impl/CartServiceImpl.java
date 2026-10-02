package com.example.e_commerceWebsite.service.impl;

import com.example.e_commerceWebsite.dto.CartRequest;
import com.example.e_commerceWebsite.dto.CartResponse;
import com.example.e_commerceWebsite.entity.Cart;
import com.example.e_commerceWebsite.entity.Product;
import com.example.e_commerceWebsite.entity.User;
import com.example.e_commerceWebsite.repository.CartRepository;
import com.example.e_commerceWebsite.repository.ProductRepository;
import com.example.e_commerceWebsite.repository.UserRepository;
import com.example.e_commerceWebsite.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public CartResponse addToCart(CartRequest request) {
        if (request.getQuantity() == null || request.getQuantity() < 1) {
            throw new IllegalArgumentException("Quantity must be at least 1");
        }

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        if (product.getQuantity() < request.getQuantity()) {
            throw new IllegalArgumentException("Insufficient product stock");
        }

        Cart cart = cartRepository.findByUserIdAndProductId(user.getId(), product.getId())
                .orElseGet(() -> Cart.builder().user(user).product(product).quantity(0).build());

        int newQuantity = cart.getQuantity() + request.getQuantity();
        if (newQuantity > product.getQuantity()) {
            throw new IllegalArgumentException("Requested quantity exceeds available stock");
        }

        cart.setQuantity(newQuantity);
        return map(cartRepository.save(cart));
    }

    @Override
    public List<CartResponse> getCart(Long userId) {
        return cartRepository.findByUserId(userId).stream().map(this::map).toList();
    }

    @Override
    public void removeCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    @Transactional
    public void clearCart(Long userId) {
        cartRepository.deleteByUserId(userId);
    }

    private CartResponse map(Cart cart) {
        return CartResponse.builder()
                .cartId(cart.getId())
                .userId(cart.getUser().getId())
                .productId(cart.getProduct().getId())
                .productName(cart.getProduct().getProductName())
                .price(cart.getProduct().getPrice())
                .quantity(cart.getQuantity())
                .build();
    }
}
