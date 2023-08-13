// package com.example.demo.service;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.ArgumentMatchers.anyLong;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import com.example.demo.model.Account;
// import com.example.demo.repository.AccountRepository;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.beans.factory.annotation.Autowired;
// import
// org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.web.servlet.MockMvc;

// import java.math.BigDecimal;
// import java.util.Optional;

// import javax.naming.InsufficientResourcesException;
// import javax.security.auth.login.AccountNotFoundException;

// @ExtendWith(MockitoExtension.class)
// @SpringBootTest
// @AutoConfigureMockMvc
// class AccountServiceTest {

// @Autowired
// private MockMvc mockMvc;

// @InjectMocks
// private AccountService accountService;

// @Mock
// private AccountRepository accountRepository;

// @ExtendWith(MockitoExtension.class) // Use MockitoExtension to initialize
// mocks
// @Test
// void testDeposit() throws AccountNotFoundException,
// InsufficientResourcesException {
// // Create a mock Account object
// Account account = new Account();
// account.setBalance(BigDecimal.valueOf(100)); // Initialize balance during
// object creation

// // Mock the accountRepository behavior
// when(accountRepository.findById(anyLong())).thenReturn(Optional.of(account));
// when(accountRepository.save(any(Account.class))).thenReturn(account);

// // Perform the deposit
// BigDecimal depositAmount = BigDecimal.valueOf(50);
// Account updatedAccount = accountService.deposit(1L, depositAmount);

// // Assertions
// assertNotNull(updatedAccount);
// assertEquals(account.getBalance().add(depositAmount),
// updatedAccount.getBalance());
// verify(accountRepository, times(1)).findById(1L);
// verify(accountRepository, times(1)).save(account);
// }

// @Test
// void testWithdrawSufficientBalance() throws AccountNotFoundException,
// InsufficientResourcesException {
// // Create a mock Account object
// Account account = new Account();
// account.setBalance(BigDecimal.valueOf(100)); // Initialize balance during
// object creation

// // Mock the accountRepository behavior
// when(accountRepository.findById(anyLong())).thenReturn(Optional.of(account));
// when(accountRepository.save(any(Account.class))).thenReturn(account);

// // Perform the withdrawal
// BigDecimal withdrawalAmount = BigDecimal.valueOf(50);
// Account updatedAccount = accountService.withdraw(1L, withdrawalAmount);

// // Assertions
// assertNotNull(updatedAccount);
// assertEquals(account.getBalance().subtract(withdrawalAmount),
// updatedAccount.getBalance());
// verify(accountRepository, times(1)).findById(1L);
// verify(accountRepository, times(1)).save(account);
// }

// @Test
// void testWithdrawInsufficientBalance() throws AccountNotFoundException {
// // Create a mock Account object
// Account account = new Account();
// account.setBalance(BigDecimal.valueOf(30)); // Initialize balance during
// object creation

// // Mock the accountRepository behavior
// when(accountRepository.findById(anyLong())).thenReturn(Optional.of(account));

// // Perform the withdrawal with an insufficient amount
// BigDecimal withdrawalAmount = BigDecimal.valueOf(50);

// assertThrows(InsufficientResourcesException.class, () -> {
// accountService.withdraw(1L, withdrawalAmount);
// });

// verify(accountRepository, times(1)).findById(1L);
// verify(accountRepository, times(0)).save(account); // Ensure that save is not
// called
// }

// }
