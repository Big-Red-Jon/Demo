package com.example.demo.service;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.model.Account;
import com.example.demo.model.Customer;
import com.example.demo.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import javax.naming.InsufficientResourcesException;
import javax.security.auth.login.AccountNotFoundException;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerService customerService;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public List<Account> getAccountsByCustomerId(Long customerId) {
        return accountRepository.findByCustomerId(customerId);
    }

    public Account deposit(Long accountId, BigDecimal amount) throws AccountNotFoundException {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();

            synchronized (account) {
                BigDecimal newBalance = account.getBalance().add(amount);
                account.setBalance(newBalance);
                return accountRepository.save(account);
            }
        } else {
            throw new AccountNotFoundException("Account not found");
        }
    }

    public Account createAccount(Long customerId, BigDecimal initialBalance, String name) {
        Customer customer = customerService.getCustomerById(customerId);
        if (customer != null) {
            Account newAccount = new Account(initialBalance, customer, name);
            return accountRepository.save(newAccount);
        }
        return null;
    }

    public void deleteAccount(Long accountId) throws AccountNotFoundException {
        Account account = getAccountById(accountId);
        accountRepository.delete(account);
    }

    public Account editAccount(Long accountId, BigDecimal newBalance) throws AccountNotFoundException {
        Account account = getAccountById(accountId);
        account.setBalance(newBalance);
        return accountRepository.save(account);
    }

    public Account withdraw(Long accountId, BigDecimal amount)
            throws AccountNotFoundException, InsufficientResourcesException {
        Optional<Account> accountOptional = accountRepository.findById(accountId);

        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();

            synchronized (account) {
                BigDecimal currentBalance = account.getBalance();

                if (currentBalance.compareTo(amount) >= 0) {
                    BigDecimal newBalance = currentBalance.subtract(amount);
                    account.setBalance(newBalance);
                    return accountRepository.save(account);
                } else {
                    throw new InsufficientResourcesException("Insufficient funds");
                }
            }
        } else {
            throw new AccountNotFoundException("Account not found");
        }
    }

    public Account getAccount(Long accountId) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        return accountOptional.orElse(null);
    }

    public BigDecimal getAccountBalance(Long accountId) {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account != null) {
            return account.getBalance();
        }
        return BigDecimal.ZERO;

    }

    public void transfer(Long sourceAccountId, Long targetAccountId, BigDecimal amount)
            throws AccountNotFoundException, InsufficientResourcesException {
        Account sourceAccount = getAccountById(sourceAccountId);
        Account targetAccount = getAccountById(targetAccountId);
        sourceAccount.transferTo(targetAccount, amount);
        accountRepository.save(sourceAccount);
        accountRepository.save(targetAccount);
    }

    private Account getAccountById(Long accountId) throws AccountNotFoundException {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
    }

}