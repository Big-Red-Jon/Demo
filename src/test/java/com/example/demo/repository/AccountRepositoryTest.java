// package com.example.demo.repository;

// import com.example.demo.model.Account;
// import com.example.demo.model.Customer;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
// import org.springframework.boot.test.context.SpringBootTest;
// // import org.springframework.boot.test.mock.mockito.MockBean;
// // import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

// import java.util.List;
// // import java.util.Optional;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// // import static org.junit.jupiter.api.Assertions.assertTrue;
// // import static org.mockito.Mockito.*;

// // @DataJpaTest
// // @SpringJUnitConfig
// @SpringBootTest
// public class AccountRepositoryTest {

// @Autowired
// private AccountRepository accountRepository;

// @Autowired
// private CustomerRepository customerRepository;

// private Customer customer;

// @BeforeEach
// void setUp() {
// // Create a real Customer entity and persist it
// customer = new Customer("John", "Doe");
// customerRepository.save(customer);
// }

// @Test
// void testFindByCustomerId() {
// // Create real Account entities
// Account account1 = new Account();
// account1.setCustomer(customer);
// accountRepository.save(account1);

// Account account2 = new Account();
// account2.setCustomer(customer);
// accountRepository.save(account2);

// // Invoke the method under test
// List<Account> retrievedAccounts = accountRepository.findByCustomer(customer);

// // Assertions
// assertNotNull(retrievedAccounts);
// assertEquals(2, retrievedAccounts.size());
// }

// }
