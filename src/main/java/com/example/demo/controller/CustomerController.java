package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.service.AccountService;
import com.example.demo.service.CustomerService;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long customerId) {
        Customer customer = customerService.getCustomer(customerId);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer newCustomer) {

        Customer createdCustomer = customerService.createCustomer(newCustomer);

        accountService.createAccount(createdCustomer.getId(), BigDecimal.ZERO, "Checking Account");

        return ResponseEntity.ok(createdCustomer);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long customerId,
            @RequestBody Customer updatedCustomer) {
        Customer customer = customerService.updateCustomer(customerId, updatedCustomer);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long customerId) {
        boolean deleted = customerService.deleteCustomer(customerId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{customerId}/name")
    public ResponseEntity<String> getCustomerName(@PathVariable Long customerId) {
        Customer customer = customerService.getCustomer(customerId);
        if (customer != null) {
            String fullName = customer.getFirstName() + " " + customer.getLastName();
            return ResponseEntity.ok(fullName);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
