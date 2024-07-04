package com.nikolaus.reactiveCart.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Product {
    private Long id;
    private String name;
    private float price;
    private String category;
    private LocalDateTime createdAt;
    private String description;
    private String imageUrl;
    private Integer stockQuantity;
    private LocalDateTime updatedAt;
}
