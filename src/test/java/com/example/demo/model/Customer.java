package com.example.demo.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

class CustomerTest {

    @Test
    void testCreateCustomerWithAccounts() {
        Customer customer = new Customer("John", "Doe");

        Account account1 = new Account();
        account1.setBalance(BigDecimal.valueOf(100));

        Account account2 = new Account();
        account2.setBalance(BigDecimal.valueOf(200));

        customer.addAccount(account1);
        customer.addAccount(account2);

        List<Account> customerAccounts = customer.getAccounts();
        assertEquals(2, customerAccounts.size());
        assertTrue(customerAccounts.contains(account1));
        assertTrue(customerAccounts.contains(account2));
    }

    @Test
    void testCreateCustomerWithoutAccounts() {
        Customer customer = new Customer("Jane", "Smith");

        List<Account> customerAccounts = customer.getAccounts();

        assertNotNull(customerAccounts);
        assertTrue(customerAccounts.isEmpty());
    }
}
