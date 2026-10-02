package com.example.e_commerceWebsite.service.impl;

import com.example.e_commerceWebsite.dto.WishlistRequest;
import com.example.e_commerceWebsite.dto.WishlistResponse;
import com.example.e_commerceWebsite.entity.Product;
import com.example.e_commerceWebsite.entity.User;
import com.example.e_commerceWebsite.entity.Wishlist;
import com.example.e_commerceWebsite.repository.ProductRepository;
import com.example.e_commerceWebsite.repository.UserRepository;
import com.example.e_commerceWebsite.repository.WishlistRepository;
import com.example.e_commerceWebsite.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {
    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public WishlistResponse addWishlist(WishlistRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        Wishlist wishlist = wishlistRepository.findByUserIdAndProductId(user.getId(), product.getId())
                .orElseGet(() -> wishlistRepository.save(
                        Wishlist.builder().user(user).product(product).build()));

        return map(wishlist);
    }

    @Override
    public List<WishlistResponse> getWishlist(Long userId) {
        return wishlistRepository.findByUserId(userId).stream().map(this::map).toList();
    }

    @Override
    public void removeWishlist(Long wishlistId) {
        wishlistRepository.deleteById(wishlistId);
    }

    private WishlistResponse map(Wishlist wishlist) {
        return WishlistResponse.builder()
                .wishlistId(wishlist.getId())
                .userId(wishlist.getUser().getId())
                .productId(wishlist.getProduct().getId())
                .productName(wishlist.getProduct().getProductName())
                .price(wishlist.getProduct().getPrice())
                .imageUrl(wishlist.getProduct().getImageUrl())
                .build();
    }
}
