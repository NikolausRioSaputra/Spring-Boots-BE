package com.example.bootcamp.service.impl;

import com.example.bootcamp.model.Account;
import com.example.bootcamp.model.AccountInfo;
import com.example.bootcamp.model.bankInfo;
import com.example.bootcamp.repository.AccountRepository;
import com.example.bootcamp.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountRepository repo;

    public List<Account> listAll() {
        return repo.findAll();
    }

    public Account get(Integer id) {

        return repo.findById(id);

    }

    public Account save(Account account){
         repo.save(account);
        return account;
//        int updated =  repo.save(account);
//        if(updated > 1) {
//            return repo.findById(account.getId());
//        } else {
//            return null;
//        }

    }

    public Account deposit(float amount, Integer id) {
        int updated = repo.deposit(amount, id);
        if(updated > 0) {
            return repo.findById(id);
        } else {
            return null;
        }
    }

    public Account withdraw(float amount, Integer id) {
        int updated = repo.withdraw(amount, id);
        if(updated > 0) {
            return repo.findById(id);
        } else {
            return null;
        }
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

    public List<AccountInfo> listAccountJoin(){
        return repo.getAccountJoin();
    }

    @Override
    public List<bankInfo> listBankJoin() {
        return  repo.getBankJoin();
    }

    ;



}