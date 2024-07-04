package com.example.bootcamp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class bankInfo {
    public Integer account_number;
    public   String account_name;
    public float balance;
    public String bank_name;
}
