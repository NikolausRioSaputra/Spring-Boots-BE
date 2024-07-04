package com.example.bootcamp.model;

import lombok.Data;

@Data
public class Amount {
    private float amount;

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
