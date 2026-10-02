package com.example.e_commerceWebsite.controller;

import com.example.e_commerceWebsite.dto.WishlistRequest;
import com.example.e_commerceWebsite.dto.WishlistResponse;
import com.example.e_commerceWebsite.service.WishlistService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
@RequiredArgsConstructor
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://localhost:5174",
        "http://localhost:5175",
        "http://localhost:5176"
})
public class WishlistController {

    private final WishlistService wishlistService;

    @PostMapping("/add")
    public WishlistResponse addWishlist(
            @RequestBody WishlistRequest request){

        return wishlistService.addWishlist(request);

    }

    @GetMapping("/{userId}")
    public List<WishlistResponse> getWishlist(
            @PathVariable Long userId){

        return wishlistService.getWishlist(userId);

    }

    @DeleteMapping("/{wishlistId}")
    public String removeWishlist(
            @PathVariable Long wishlistId){

        wishlistService.removeWishlist(wishlistId);

        return "Wishlist Item Removed Successfully";
    }

}
