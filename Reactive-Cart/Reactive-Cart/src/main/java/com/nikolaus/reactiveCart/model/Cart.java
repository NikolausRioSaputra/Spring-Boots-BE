package com.nikolaus.reactiveCart.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@Table("cart")
public class Cart {

    @Id
    private Long id;

    private LocalDateTime createAt;

    private String status;

    private float totalPrice;

    private LocalDateTime updatedAt;

    public Cart(Long id,LocalDateTime createdAt, String status, float totalPrice, LocalDateTime updatedAt) {
        this.id = id;
        this.createAt = createdAt;
        this.status = status;
        this.totalPrice = totalPrice;
        this.updatedAt = updatedAt;
    }
}
