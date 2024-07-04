package com.example.bootcamp.repository.impl;

import com.example.bootcamp.model.Account;
import com.example.bootcamp.model.AccountInfo;
import com.example.bootcamp.model.bankInfo;
import com.example.bootcamp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcAccountRepository implements AccountRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Account save(Account account) {
        try {
            jdbcTemplate.update("INSERT INTO accounts (account_number, balance) VALUES(?,?)",
                    new Object[] { account.getAccountNumber(), account.getBalance() });
        }catch(Exception e){
            System.out.println("ok");
        }

        return account;
    }

    @Override
    public int update(Account account) {
        return jdbcTemplate.update("UPDATE accounts SET account_number=?, balance=? WHERE id=?",
                new Object[] { account.getAccountNumber(), account.getBalance(), account.getId() });
    }

    @Override
    public Account findById(Integer id) {
        Account account = jdbcTemplate.queryForObject("SELECT * FROM accounts WHERE id=?",
                BeanPropertyRowMapper.newInstance(Account.class), id);
        return account;
    }

    @Override
    public List<Account> findAll() {
        return jdbcTemplate.query("SELECT * from accounts", BeanPropertyRowMapper.newInstance(Account.class));
    }

    @Override
    public boolean existsById(Integer id) {
        Integer count = this.jdbcTemplate.queryForObject("select count(*) from accounts WHERE id=?", Integer.class, id);
        if(count > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int deleteById(Integer id) {
        return jdbcTemplate.update("DELETE FROM accounts WHERE id=?", id);
    }

    @Override
    public int deposit(float amount, Integer id) {
        return jdbcTemplate.update("UPDATE accounts SET balance=balance + ? WHERE id=?",
                new Object[] { amount, id });
    }

    @Override
    public int withdraw(float amount, Integer id) {
        return jdbcTemplate.update("UPDATE accounts SET balance=balance - ? WHERE id=?",
                new Object[] { amount, id });
    }

    @Override
    public List<AccountInfo> getAccountJoin() {
        return jdbcTemplate.query("SELECT \n" +
                "\ta.id,\n" +
                "\tb.account_name, \n" +
                "\ta.balance\n" +
                "FROM \n" +
                "\taccounts a join account_info b on a.id = b.accounts_id", BeanPropertyRowMapper.newInstance(AccountInfo.class));
    }

    @Override
    public List<bankInfo> getBankJoin() {
        return jdbcTemplate.query("SELECT \n" +
                "\tA.account_number,\n" +
                "\tB.account_name,\n" +
                "\tA.balance,\n" +
                "\tC.bank_name\n" +
                "FROM \n" +
                "\taccounts A \n" +
                "\tINNER JOIN account_info B on A.id = B.accounts_id\n" +
                "\tINNER JOIN bank_info C on A.id = C.accounts_id", BeanPropertyRowMapper.newInstance(bankInfo.class));
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM accounts ");
    }

}
