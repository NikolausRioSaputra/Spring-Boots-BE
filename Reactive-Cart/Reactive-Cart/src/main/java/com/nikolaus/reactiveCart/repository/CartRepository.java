package com.nikolaus.reactiveCart.repository;

import com.nikolaus.reactiveCart.model.Cart;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface CartRepository extends R2dbcRepository<Cart, Long> {
}
