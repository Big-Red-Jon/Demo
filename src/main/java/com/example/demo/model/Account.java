package com.example.demo.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

// Account.java
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal balance;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal add) {
    }

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public void transferTo(Account targetAccount, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        if (this.equals(targetAccount)) {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }

        if (this.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        this.setBalance(this.getBalance().subtract(amount));
        targetAccount.setBalance(targetAccount.getBalance().add(amount));
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    // Getters and setters
}