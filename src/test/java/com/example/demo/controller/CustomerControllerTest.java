package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testGetCustomer_ValidCustomer() {
        Long customerId = 1L;
        Customer mockCustomer = new Customer("John", "Doe");
        when(customerService.getCustomer(customerId)).thenReturn(mockCustomer);

        ResponseEntity<Customer> response = customerController.getCustomer(customerId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(mockCustomer, response.getBody());

        verify(customerService).getCustomer(customerId);
    }

    @Test
    void testGetCustomer_CustomerNotFound() {
        Long customerId = 1L;
        when(customerService.getCustomer(customerId)).thenReturn(null);

        ResponseEntity<Customer> response = customerController.getCustomer(customerId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

        verify(customerService).getCustomer(customerId);
    }

}
