package com.example.alisverisapp.controller;

import com.example.alisverisapp.model.Cart;
import com.example.alisverisapp.service.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/{cartId}/addProduct")
    public Cart addProductToCart(
            @PathVariable Long cartId,
            @RequestParam Long productId,
            @RequestParam int quantity) {
        return cartService.addProductToCart(cartId, productId, quantity);
    }

    @PutMapping("/{cartId}/updateProduct")
    public Cart updateCart(
            @PathVariable Long cartId,
            @RequestParam Long productId,
            @RequestParam int quantity) {
        return cartService.updateCart(cartId, productId, quantity);
    }

    @DeleteMapping("/{cartId}/removeProduct")
    public Cart removeProductFromCart(
            @PathVariable Long cartId,
            @RequestParam Long productId) {
        return cartService.removeProductFromCart(cartId, productId);
    }

    @PostMapping("/{cartId}/empty")
    public Cart emptyCart(@PathVariable Long cartId) {
        return cartService.emptyCart(cartId);
    }
}
