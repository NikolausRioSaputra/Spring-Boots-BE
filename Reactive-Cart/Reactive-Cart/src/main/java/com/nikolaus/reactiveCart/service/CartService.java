package com.nikolaus.reactiveCart.service;

import com.nikolaus.reactiveCart.model.Cart;
import com.nikolaus.reactiveCart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    public Flux<Cart> findAllbyCart(){
        return cartRepository.findAll();
    }

    public Mono<Cart> findCartById(Long id){
        return cartRepository.findById(id);
    }

    public Mono<Cart> save(Cart cart){
        return cartRepository.save(cart);
    }

    public Mono<Cart> update(Long id, Cart cart){
        return cartRepository.findById(id)
                .map(Optional::of)
                .defaultIfEmpty(Optional.empty())
                .flatMap(optionalCart ->{
                    if(optionalCart.isPresent()){
                        cart.setId(id);
                        return cartRepository.save(cart);
                    }
                    return Mono.empty();
                });
    }

    public  Mono<Void> delete(Long id){
        return cartRepository.deleteById(id);
    }

}
