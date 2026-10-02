package com.example.e_commerceWebsite.service;

import com.example.e_commerceWebsite.dto.CartRequest;
import com.example.e_commerceWebsite.dto.CartResponse;

import java.util.List;

public interface CartService {

    CartResponse addToCart(CartRequest request);

    List<CartResponse> getCart(Long userId);

    void removeCart(Long cartId);

}
