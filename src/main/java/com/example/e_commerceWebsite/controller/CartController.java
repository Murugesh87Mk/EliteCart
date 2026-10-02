package com.example.e_commerceWebsite.controller;

import com.example.e_commerceWebsite.dto.CartRequest;
import com.example.e_commerceWebsite.dto.CartResponse;
import com.example.e_commerceWebsite.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://localhost:5174",
        "http://localhost:5175",
        "http://localhost:5176"
})
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public CartResponse addToCart(@RequestBody CartRequest request){

        return cartService.addToCart(request);

    }

    @GetMapping("/{userId}")
    public List<CartResponse> getCart(@PathVariable Long userId){

        return cartService.getCart(userId);

    }

    @DeleteMapping("/{cartId}")
    public String removeCart(@PathVariable Long cartId){

        cartService.removeCart(cartId);

        return "Cart Item Removed";

    }

}
