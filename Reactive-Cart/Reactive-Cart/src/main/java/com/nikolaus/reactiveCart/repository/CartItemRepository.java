package com.nikolaus.reactiveCart.repository;

import com.nikolaus.reactiveCart.model.CartItem;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface CartItemRepository extends R2dbcRepository<CartItem, Long> {
}
