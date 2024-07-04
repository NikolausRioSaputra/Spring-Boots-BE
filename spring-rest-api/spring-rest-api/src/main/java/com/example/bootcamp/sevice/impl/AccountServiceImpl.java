package com.example.bootcamp.sevice.impl;

import com.example.bootcamp.exception.AccountAlreadyExistsException;
import com.example.bootcamp.exception.NoSuchAccountExistsException;
import com.example.bootcamp.model.Account;
import com.example.bootcamp.repository.AccountRepository;
import com.example.bootcamp.service.AccountService;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

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
        try {
            return repo.findById(id).get();
        }catch(NoSuchElementException e) {
            throw new NoSuchAccountExistsException("No Such Account exists!");
        }
    }

    public Account save(Account account){
        try {
            return repo.save(account);
        }catch(DataIntegrityViolationException e) {
            throw new AccountAlreadyExistsException("Account already exists!", e);
        }catch(ConstraintViolationException e) {
            throw new AccountAlreadyExistsException("Account already exists!", e);
        }
    }

    public Account deposit(float amount, Integer id) {
        boolean isExists = repo.existsById(id);

        if(isExists) {
            repo.deposit(amount, id);
            return repo.findById(id).get();
        } else {
            throw new NoSuchAccountExistsException("No Such Account exists!");
        }
    }

    public Account withdraw(float amount, Integer id) {
        boolean isExists = repo.existsById(id);

        if(isExists) {
            repo.withdraw(amount, id);
            return repo.findById(id).get();
        } else {
            throw new NoSuchAccountExistsException("No Such Account exists!");
        }
    }

    public void delete(Integer id) {
        boolean isExists = repo.existsById(id);

        if(isExists) {
            repo.deleteById(id);
        } else {
            throw new NoSuchAccountExistsException("No Such Account exists!");
        }
    }
}
