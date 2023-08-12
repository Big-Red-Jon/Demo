package com.example.demo.repository;

import java.util.List;
import java.util.Optional;
import com.example.demo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByCustomerId(Long customerId);

    Optional<Account> findById(Long accountId);


}
