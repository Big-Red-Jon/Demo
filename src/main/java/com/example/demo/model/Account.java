package com.example.demo.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal balance;

    private String name;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal add) {
        this.balance = add;
    }

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Account() {
    }

    public Account(BigDecimal balance, Customer customer, String name) {
        this.balance = balance;
        this.customer = customer;
        this.name = name;
    }

    public void transferTo(Account targetAccount, BigDecimal amount) {
        if (targetAccount == null) {
            throw new IllegalArgumentException("Target account cannot be null");
        }

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive and non-null");
        }

        if (this.equals(targetAccount)) {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }

        if (this.getBalance() == null || this.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient balance or balance is null");
        }

        this.setBalance(this.getBalance().subtract(amount));
        targetAccount.setBalance(targetAccount.getBalance().add(amount));
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}