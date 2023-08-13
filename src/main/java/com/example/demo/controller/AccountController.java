package com.example.demo.controller;

import com.example.demo.model.Account;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InsufficientResourcesException;
import javax.security.auth.login.AccountNotFoundException;

import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccount(@PathVariable Long accountId) {
        Account account = accountService.getAccount(accountId);
        if (account != null) {
            return ResponseEntity.ok(account);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(
            @RequestParam Long customerId,
            @RequestParam BigDecimal initialBalance,
            @RequestParam String name) {
        Account newAccount = accountService.createAccount(customerId, initialBalance, name);
        if (newAccount != null) {
            return ResponseEntity.ok(newAccount);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{accountId}/edit")
    public ResponseEntity<Account> editAccount(@PathVariable Long accountId, @RequestParam BigDecimal newBalance) {
        try {
            Account updatedAccount = accountService.editAccount(accountId, newBalance);
            return ResponseEntity.ok(updatedAccount);
        } catch (AccountNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{accountId}/delete")
    public ResponseEntity<?> deleteAccount(@PathVariable Long accountId) {
        try {
            accountService.deleteAccount(accountId);
            return ResponseEntity.ok("Account deleted successfully");
        } catch (AccountNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{accountId}/deposit")
    public ResponseEntity<Account> deposit(@PathVariable Long accountId, @RequestParam BigDecimal amount) {
        try {
            Account updatedAccount = accountService.deposit(accountId, amount);
            return ResponseEntity.ok(updatedAccount);
        } catch (AccountNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{accountId}/withdraw")
    public ResponseEntity<?> withdraw(@PathVariable Long accountId, @RequestParam BigDecimal amount) {
        try {
            Account updatedAccount = accountService.withdraw(accountId, amount);
            return ResponseEntity.ok(updatedAccount);
        } catch (AccountNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (InsufficientResourcesException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Insufficient funds for withdrawal");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/{accountId}/balance")
    public ResponseEntity<BigDecimal> getAccountBalance(@PathVariable Long accountId) {
        BigDecimal balance = accountService.getAccountBalance(accountId);
        if (balance != null) {
            return ResponseEntity.ok(balance);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/transfer")
    public ResponseEntity<?> transferFunds(
            @RequestParam Long sourceAccountId,
            @RequestParam Long targetAccountId,
            @RequestParam BigDecimal amount) {
        try {
            accountService.transfer(sourceAccountId, targetAccountId, amount);
            return ResponseEntity.ok("Funds transferred successfully");
        } catch (AccountNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (InsufficientResourcesException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Insufficient funds for transfer");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

}