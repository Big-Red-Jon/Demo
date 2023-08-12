package com.example.demo.model;

import java.math.BigDecimal;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

// @Entity
// public class Account {
//     @Id
//     private Long id;

//     public BigDecimal getBalance() {
//         return null;
//     }

//     public void setBalance(BigDecimal add) {
//     }
// }

// Account.java
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public BigDecimal getBalance() {
        return null;
    }

    public void setBalance(BigDecimal add) {
    }

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // Getters and setters
}