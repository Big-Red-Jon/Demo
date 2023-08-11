package com.example.demo.service;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.model.Account;

import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

// AccountService.java
@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public List<Account> getAccountsByCustomerId(Long customerId) {
        return accountRepository.findByCustomerId(customerId);
    }

    public Account deposit(Long accountId, BigDecimal amount) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            account.setBalance(account.getBalance().add(amount));
            return accountRepository.saveAccount(account);
        }
        return null; // Handle account not found
    }

    public Account withdraw(Long accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account != null && account.getBalance().compareTo(amount) >= 0) {
            account.setBalance(account.getBalance().subtract(amount));
            return accountRepository.saveAccount(account);
        }
        return null; // Handle insufficient funds or account not found
    }

    public BigDecimal getAccountBalance(Long accountId) {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account != null) {
            return account.getBalance();
        }
        return BigDecimal.ZERO; // Handle account not found
    }

    public Account createAccount(Account newAccount) {
        return null;
    }

    public Account getAccount(Long accountId) {
        return null;
    }

    public Account updateAccount(Long accountId, Account updatedAccount) {
        return null;
    }

    public boolean deleteAccount(Long accountId) {
        return false;
    }

}