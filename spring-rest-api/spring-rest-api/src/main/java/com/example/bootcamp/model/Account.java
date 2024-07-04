package com.example.bootcamp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 20)
    private String accountNumber;

    private float balance;

    public Account(String accountNumber, float balance){
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

}
