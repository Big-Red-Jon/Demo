package com.example.demo.repository;

import java.util.Optional;

import com.example.demo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Object findById(Long id);

    Optional<Customer> findById(Long id);

    Customer save(Long customerId);

    // Customer save(Customer customer);

    void deleteById(Long id);

    void delete(Customer customer);

}
