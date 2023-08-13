package com.example.demo.repository;

import com.example.demo.model.Account;
import com.example.demo.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@DataJpaTest
@SpringJUnitConfig
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @MockBean
    private CustomerRepository customerRepository;

    @Test
    void testFindByCustomerId() {
        Long customerId = 1L;

        // Mock a list of accounts associated with the customer
        List<Account> accounts = List.of(
                new Account(),
                new Account());

        // Mock a Customer object
        Customer customer = mock(Customer.class);
        when(customer.getId()).thenReturn(customerId); // Mocking the getId() method

        // Configure the mock CustomerRepository to return the mock Customer
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        // Configure the mock AccountRepository to return the list of accounts
        when(accountRepository.findByCustomer(customer)).thenReturn(accounts);

        // Invoke the method under test
        List<Account> retrievedAccounts = accountRepository.findByCustomerId(customerId);

        // Assertions and verifications
        assertNotNull(retrievedAccounts);
        assertEquals(2, retrievedAccounts.size());
        assertTrue(retrievedAccounts.containsAll(accounts));

        // Verify that the customer repository's findById method was called
        verify(customerRepository).findById(customerId);

        // Verify that the account repository's findByCustomer method was called
        verify(accountRepository).findByCustomer(customer);
    }

    // Similar test methods for other methods in AccountRepository...
}
