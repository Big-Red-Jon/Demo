package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// CustomerService.java
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // public Customer createCustomer(Customer newCustomer) {
    // return null;
    // }

    public Customer getCustomer(Long customerId) {
        return null;
    }

    public Customer updateCustomer(Long customerId, Customer updatedCustomer) {
        return customerRepository.save(customerId);
    }

    // public boolean deleteCustomer(Long customerId) {
    // return false;
    // }

    public Customer getCustomerById(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        return customerOptional.orElse(null);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public boolean deleteCustomer(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            customerRepository.delete(customerOptional.get());
            return true; // Customer was deleted successfully
        } else {
            return false; // Customer was not found
        }
    }
}
