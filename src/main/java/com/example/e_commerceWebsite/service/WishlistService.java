package com.example.e_commerceWebsite.service;

import com.example.e_commerceWebsite.dto.WishlistRequest;
import com.example.e_commerceWebsite.dto.WishlistResponse;

import java.util.List;

public interface WishlistService {

    WishlistResponse addWishlist(WishlistRequest request);

    List<WishlistResponse> getWishlist(Long userId);

    void removeWishlist(Long wishlistId);

}