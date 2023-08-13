package com.example.demo.repository;

import java.util.List;
import java.util.Optional;
import com.example.demo.model.Account;
import com.example.demo.model.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByCustomerId(Long customerId);

    List<Account> findByCustomer(Customer customer);

    Optional<Account> findById(Long accountId);

}
