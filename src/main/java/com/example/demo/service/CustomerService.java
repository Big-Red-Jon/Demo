package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        return customerOptional.orElse(null);
    }

    public Customer getCustomer(Long customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }

    // public Customer updateCustomer(Long customerId, Customer updatedCustomer) {
    // Customer existingCustomer =
    // customerRepository.findById(customerId).orElse(null);
    // if (existingCustomer != null) {
    // // Update properties of existingCustomer with properties from updatedCustomer
    // // Example: existingCustomer.setName(updatedCustomer.getName());
    // // ...
    // return customerRepository.save(existingCustomer);
    // }
    // return null;
    // }

    // public Customer createCustomer(Customer customer) {
    // return customerRepository.save(customer);
    // }

    // public Customer updateCustomerDetails(Customer updatedCustomer) {
    // return customerRepository.save(updatedCustomer);
    // }

    // public boolean deleteCustomer(Long id) {
    // Optional<Customer> customerOptional = customerRepository.findById(id);
    // if (customerOptional.isPresent()) {
    // customerRepository.delete(customerOptional.get());
    // return true; // Customer was deleted successfully
    // } else {
    // return false; // Customer was not found
    // }
    // }
}
