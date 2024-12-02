package com.abcbank.controller;

import com.abcbank.dto.AccountDTO;
import com.abcbank.dto.TransactionDTO;
import com.abcbank.service.AccountService;
import com.abcbank.service.impl.AccountServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@Validated
public class AccountController {

    @Autowired
    private AccountServiceImpl accountService;


    /**
     * Get account details by ID.
     *
     * @param accountId The account ID.
     * @return AccountDTO with account details.
     */
    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long accountId) {
        AccountDTO accountDTO = accountService.getAccountById(accountId);
        return ResponseEntity.ok(accountDTO);
    }

    /**
     * Create a new account for a customer.
     *
     * @param customerId  The customer ID.
     * @param accountDTO  The account details.
     * @return Success message.
     */
    @PostMapping("/customer/{customerId}")
    public ResponseEntity<String> createAccount(
            @PathVariable Long customerId,
            @Valid @RequestBody AccountDTO accountDTO) {
        accountService.createAccount(customerId, accountDTO);
        return ResponseEntity.ok("Account created successfully!");
    }

    /**
     * Update account details.
     *
     * @param accountId   The account ID.
     * @param accountDTO  The updated account details.
     * @return Success message.
     */
    @PutMapping("/{accountId}")
    public ResponseEntity<String> updateAccount(
            @PathVariable Long accountId,
            @Valid @RequestBody AccountDTO accountDTO) {
        accountService.updateAccount(accountId, accountDTO);
        return ResponseEntity.ok("Account updated successfully!");
    }

    /**
     * Delete an account by ID.
     *
     * @param accountId The account ID.
     * @return Success message.
     */
    @DeleteMapping("/{accountId}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long accountId) {
        accountService.deleteAccount(accountId);
        return ResponseEntity.ok("Account deleted successfully!");
    }

    /**
     * Get all accounts.
     *
     * @return List of AccountDTO.
     */
    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        List<AccountDTO> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    /**
     * Deposit money into an account.
     *
     * @param accountId The account ID.
     * @param amount    The deposit amount.
     * @return Success message.
     */
    @PostMapping("/{accountId}/deposit")
    public ResponseEntity<String> depositToAccount(
            @PathVariable Long accountId,
            @RequestParam Double amount) {
        accountService.depositToAccount(accountId, amount);
        return ResponseEntity.ok("Amount deposited successfully!");
    }

    /**
     * Withdraw money from an account.
     *
     * @param accountId The account ID.
     * @param amount    The withdrawal amount.
     * @return Success message.
     */
    @PostMapping("/{accountId}/withdraw")
    public ResponseEntity<String> withdrawFromAccount(
            @PathVariable Long accountId,
            @RequestParam Double amount) {
        accountService.withdrawFromAccount(accountId, amount);
        return ResponseEntity.ok("Amount withdrawn successfully!");
    }

    /**
     * Get transactions for an account.
     *
     * @param accountId The account ID.
     * @return List of TransactionDTO.
     */
    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<List<TransactionDTO>> getTransactionsByAccountId(@PathVariable Long accountId) {
        List<TransactionDTO> transactions = accountService.getTransactionsByAccountId(accountId);
        return ResponseEntity.ok(transactions);
    }
}

