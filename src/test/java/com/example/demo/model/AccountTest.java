package com.example.demo.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class AccountTest {

    @Test
    void testTransferToValid() {
        Account sourceAccount = new Account();
        sourceAccount.setBalance(BigDecimal.valueOf(100));

        Account targetAccount = new Account();
        targetAccount.setBalance(BigDecimal.valueOf(50));

        BigDecimal transferAmount = BigDecimal.valueOf(30);

        sourceAccount.transferTo(targetAccount, transferAmount);

        assertEquals(sourceAccount.getBalance(), BigDecimal.valueOf(70));
        assertEquals(targetAccount.getBalance(), BigDecimal.valueOf(80));
    }

    @Test
    void testTransferToSameAccount() {
        Account account = new Account();
        account.setBalance(BigDecimal.valueOf(100));

        BigDecimal transferAmount = BigDecimal.valueOf(50);

        assertThrows(IllegalArgumentException.class, () -> {
            account.transferTo(account, transferAmount);
        });
    }

    @Test
    void testTransferToInsufficientBalance() {
        Account sourceAccount = new Account();
        sourceAccount.setBalance(BigDecimal.valueOf(50));

        Account targetAccount = new Account();
        targetAccount.setBalance(BigDecimal.valueOf(30));

        BigDecimal transferAmount = BigDecimal.valueOf(60);

        assertThrows(IllegalArgumentException.class, () -> {
            sourceAccount.transferTo(targetAccount, transferAmount);
        });
    }
}
