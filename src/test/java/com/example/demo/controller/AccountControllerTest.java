package com.example.demo.controller;

import com.example.demo.model.Account;
import com.example.demo.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.naming.InsufficientResourcesException;
import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @Test
    void testGetAccount_ValidAccount() {
        Long accountId = 1L;
        Account mockAccount = new Account();
        when(accountService.getAccount(accountId)).thenReturn(mockAccount);

        ResponseEntity<Account> response = accountController.getAccount(accountId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(mockAccount, response.getBody());

        verify(accountService).getAccount(accountId);
    }

    @Test
    void testGetAccount_AccountNotFound() {
        Long accountId = 1L;
        when(accountService.getAccount(accountId)).thenReturn(null);

        ResponseEntity<Account> response = accountController.getAccount(accountId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

        verify(accountService).getAccount(accountId);
    }

    @Test
    void testDeposit_ValidDeposit() throws AccountNotFoundException {
        Long accountId = 1L;
        BigDecimal depositAmount = BigDecimal.valueOf(100);
        Account mockUpdatedAccount = new Account();
        when(accountService.deposit(accountId, depositAmount)).thenReturn(mockUpdatedAccount);

        ResponseEntity<Account> response = accountController.deposit(accountId, depositAmount);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(mockUpdatedAccount, response.getBody());

        verify(accountService).deposit(accountId, depositAmount);
    }

    @Test
    void testDeposit_AccountNotFound() throws AccountNotFoundException {
        Long accountId = 1L;
        BigDecimal depositAmount = BigDecimal.valueOf(100);
        when(accountService.deposit(accountId, depositAmount)).thenThrow(new AccountNotFoundException());

        ResponseEntity<Account> response = accountController.deposit(accountId, depositAmount);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

        verify(accountService).deposit(accountId, depositAmount);
    }

    @Test
    void testWithdraw_ValidWithdrawal() throws AccountNotFoundException, InsufficientResourcesException {
        Long accountId = 1L;
        BigDecimal withdrawalAmount = BigDecimal.valueOf(50);
        Account mockUpdatedAccount = new Account();
        when(accountService.withdraw(accountId, withdrawalAmount)).thenReturn(mockUpdatedAccount);

        ResponseEntity<?> response = accountController.withdraw(accountId, withdrawalAmount);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(mockUpdatedAccount, response.getBody());

        verify(accountService).withdraw(accountId, withdrawalAmount);
    }

    @Test
    void testWithdraw_AccountNotFound() throws AccountNotFoundException, InsufficientResourcesException {
        Long accountId = 1L;
        BigDecimal withdrawalAmount = BigDecimal.valueOf(50);
        when(accountService.withdraw(accountId, withdrawalAmount)).thenThrow(new AccountNotFoundException());

        ResponseEntity<?> response = accountController.withdraw(accountId, withdrawalAmount);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

        verify(accountService).withdraw(accountId, withdrawalAmount);
    }

    @Test
    void testWithdraw_InsufficientFunds() throws AccountNotFoundException, InsufficientResourcesException {
        Long accountId = 1L;
        BigDecimal withdrawalAmount = BigDecimal.valueOf(50);
        when(accountService.withdraw(accountId, withdrawalAmount)).thenThrow(new InsufficientResourcesException());

        ResponseEntity<?> response = accountController.withdraw(accountId, withdrawalAmount);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof Map);

        Map<String, String> errorResponse = (Map<String, String>) response.getBody();
        assertEquals("Insufficient funds for withdrawal", errorResponse.get("error"));

        verify(accountService).withdraw(accountId, withdrawalAmount);
    }

    @Test
    void testGetAccountBalance_ValidBalance() {
        Long accountId = 1L;
        BigDecimal mockBalance = BigDecimal.valueOf(500);
        when(accountService.getAccountBalance(accountId)).thenReturn(mockBalance);

        ResponseEntity<BigDecimal> response = accountController.getAccountBalance(accountId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(mockBalance, response.getBody());

        verify(accountService).getAccountBalance(accountId);
    }

    @Test
    void testGetAccountBalance_AccountNotFound() {
        Long accountId = 1L;
        when(accountService.getAccountBalance(accountId)).thenReturn(null);

        ResponseEntity<BigDecimal> response = accountController.getAccountBalance(accountId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

        verify(accountService).getAccountBalance(accountId);
    }

}
