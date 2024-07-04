package com.example.bootcamp.service;

import com.example.bootcamp.model.Account;
import com.example.bootcamp.model.AccountInfo;
import com.example.bootcamp.model.bankInfo;

import java.util.List;

public interface AccountService {
    List<Account> listAll();

    Account get(Integer id);

    Account save(Account account);

    Account deposit(float amount, Integer id);

    Account withdraw(float amount, Integer id);

    void delete(Integer id);

    List<AccountInfo> listAccountJoin();

    List<bankInfo> listBankJoin();
}
