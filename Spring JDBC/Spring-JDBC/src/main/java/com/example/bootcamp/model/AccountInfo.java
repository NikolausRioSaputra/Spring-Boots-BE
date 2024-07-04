package com.example.bootcamp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountInfo {
    private Integer accountNumber;
    private String accountName;
    private String balance;
}
