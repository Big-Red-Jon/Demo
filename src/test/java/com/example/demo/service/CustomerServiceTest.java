// package com.example.demo.service;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;

// import com.example.demo.model.Customer;
// import com.example.demo.repository.CustomerRepository;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;

// @ExtendWith(MockitoExtension.class)
// public class CustomerServiceTest {

// @Mock
// private CustomerRepository customerRepository;

// @InjectMocks
// private CustomerService customerService;

// @BeforeEach
// void setUp() {
// }

// @Test
// void testGetAllCustomers() {
// // Mock data
// List<Customer> mockCustomers = new ArrayList<>();
// mockCustomers.add(new Customer("John", "Doe"));
// mockCustomers.add(new Customer("Jane", "Smith"));

// // Mock repository behavior
// when(customerRepository.findAll()).thenReturn(mockCustomers);

// // Invoke the service method
// List<Customer> customers = customerService.getAllCustomers();

// // Assertions
// assertEquals(2, customers.size());
// assertEquals(mockCustomers, customers);

// // Verify that the repository's findAll method was called
// verify(customerRepository).findAll();
// }

// @Test
// void testGetCustomerById() {
// // Mock data
// Long customerId = 1L;
// Customer mockCustomer = new Customer("John", "Doe");
// mockCustomer.setId(customerId);

// // Mock repository behavior
// when(customerRepository.findById(customerId)).thenReturn(Optional.of(mockCustomer));

// // Invoke the service method
// Customer retrievedCustomer = customerService.getCustomerById(customerId);

// // Assertions
// assertNotNull(retrievedCustomer);
// assertEquals(customerId, retrievedCustomer.getId());

// // Verify that the repository's findById method was called
// verify(customerRepository).findById(customerId);
// }

// @Test
// void testGetCustomerById_NotFound() {
// // Mock data
// Long customerId = 1L;

// // Mock repository behavior
// when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

// // Invoke the service method
// Customer retrievedCustomer = customerService.getCustomerById(customerId);

// // Assertion
// assertNull(retrievedCustomer);

// // Verify that the repository's findById method was called
// verify(customerRepository).findById(customerId);
// }

// // Add more tests for other methods as needed
// }
