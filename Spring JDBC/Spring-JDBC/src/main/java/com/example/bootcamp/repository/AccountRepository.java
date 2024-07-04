package com.example.bootcamp.repository;

import com.example.bootcamp.model.Account;
import com.example.bootcamp.model.AccountInfo;
import com.example.bootcamp.model.bankInfo;

import java.util.List;

public interface AccountRepository {
    Account save(Account accountNumber);
    int update(Account account);
    Account findById(Integer id);
    List<Account> findAll();
    boolean existsById(Integer id);
    int deleteById(Integer id);
    int deposit(float amount, Integer id);
    int withdraw(float amount, Integer id);

    List<AccountInfo> getAccountJoin();

    List<bankInfo> getBankJoin();

    void deleteAll();
}