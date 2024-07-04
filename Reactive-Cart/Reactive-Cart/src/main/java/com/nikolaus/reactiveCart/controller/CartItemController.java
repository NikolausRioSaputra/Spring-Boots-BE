package com.nikolaus.reactiveCart.controller;

import com.nikolaus.reactiveCart.model.CartItem;
import com.nikolaus.reactiveCart.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/cart-items")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;

    @GetMapping
    public Flux<CartItem> getAllCartItems() {
        return cartItemService.getAllCartItems();
    }

    @GetMapping("/{id}")
    public Mono<CartItem> getCartItemById(@PathVariable Long id) {
        return cartItemService.getCartItemById(id);
    }

    @PostMapping
    public Mono<CartItem> createCartItem(@RequestBody CartItem cartItem) {
        return cartItemService.createCartItem(cartItem);
    }

    @PutMapping("/{id}")
    public Mono<CartItem> updateCartItem(@PathVariable Long id, @RequestBody CartItem cartItem) {
        return cartItemService.updateCartItem(id, cartItem);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteCartItem(@PathVariable Long id) {
        return cartItemService.deleteCartItem(id);
    }
}
