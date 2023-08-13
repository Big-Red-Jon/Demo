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

    public Customer createCustomer(Customer newCustomer) {
        Customer customer = new Customer();
        customer.setFirstName(newCustomer.getFirstName());
        customer.setLastName(newCustomer.getLastName());

        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long customerId, Customer updatedCustomer) {
        Customer existingCustomer = customerRepository.findById(customerId).orElse(null);
        if (existingCustomer != null) {

            existingCustomer.setFirstName(updatedCustomer.getFirstName());
            existingCustomer.setLastName(updatedCustomer.getLastName());

            return customerRepository.save(existingCustomer);
        }
        return null;
    }

    public boolean deleteCustomer(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            customerRepository.delete(customerOptional.get());
            return true;
        } else {
            return false;
        }
    }
}
