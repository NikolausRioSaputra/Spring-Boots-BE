package com.nikolaus.reactiveCart.service;

import com.nikolaus.reactiveCart.model.CartItem;
import com.nikolaus.reactiveCart.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CartItemService {

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    OrchestratorService orchestratorService;

    public Flux<CartItem> getAllCartItems(){
        return cartItemRepository.findAll();
    }

    public Mono<CartItem> getCartItemById(Long id){
        return cartItemRepository.findById(id)
                .flatMap(cartItem -> orchestratorService.getproductById(cartItem.getProductId())
                        .map(product -> {
                            return cartItem;
                        }));
    }

    public Mono<CartItem> createCartItem(CartItem cartItem){
        return orchestratorService.getproductById(cartItem.getProductId())
                .flatMap(product -> cartItemRepository.save(cartItem))
                .switchIfEmpty(Mono.error(new RuntimeException("Product not found")));
    }

    public Mono<CartItem> updateCartItem(Long id, CartItem cartItem){
        return cartItemRepository.findById(id)
                .flatMap(existingCartItem -> {
                    existingCartItem.setProductId(cartItem.getProductId());
                    existingCartItem.setQuantity(cartItem.getQuantity());
                    existingCartItem.setCartId(cartItem.getCartId());
                    return cartItemRepository.save(existingCartItem);
                });
    }

    public Mono<Void> deleteCartItem(Long id){
        return cartItemRepository.deleteById(id);
    }
}
