package com.example.bootcamp.repository;

import com.example.bootcamp.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query("UPDATE Account a SET a.balance = a.balance + ?1 Where a.id = ?2")
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    public void deposit(float amount, Integer id);

    @Query("UPDATE Account a SET a.balance = a.balance - ?1 WHERE a.id = ?2")
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    public void withdraw(float amount, Integer id);
}
