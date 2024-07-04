package com.nikolaus.reactiveCart.controller;

import com.nikolaus.reactiveCart.model.Cart;
import com.nikolaus.reactiveCart.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Cart>getAllCart(){
        return cartService.findAllbyCart();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Cart> getCartById(@PathVariable("id") Long id){
        return  cartService.findCartById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Cart> createCart(@RequestBody Cart cart){
        log.info("yang d kkirim {}" + cart);
        return cartService.save(cart);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Cart> updateCart (@PathVariable("id") Long id, @RequestBody Cart cart){
        return cartService.update(id, cart);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteCart(@PathVariable Long id){
        return cartService.delete(id);
    }
}
