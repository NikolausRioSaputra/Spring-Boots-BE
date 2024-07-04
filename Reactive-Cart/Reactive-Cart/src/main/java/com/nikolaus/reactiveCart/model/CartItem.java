package com.nikolaus.reactiveCart.model;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@Builder
@Table("cart_item")
public class CartItem {

    @Id
    private Long id;

    private Long productId;

    private Integer quantity;

    private Long cartId;

    public CartItem(Long id, Long productId, Integer quantity, Long cartId) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.cartId = cartId;
    }
}
