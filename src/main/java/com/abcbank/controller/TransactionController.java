package com.abcbank.controller;

import com.abcbank.dto.TransactionDTO;
import com.abcbank.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@Validated
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Get a transaction by ID.
     *
     * @param transactionId The ID of the transaction.
     * @return The details of the transaction as TransactionDTO.
     */
    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable Long transactionId) {
        TransactionDTO transaction = transactionService.getTransactionById(transactionId);
        return ResponseEntity.ok(transaction);
    }

    /**
     * Get all transactions.
     *
     * @return List of all transactions as TransactionDTOs.
     */
    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        List<TransactionDTO> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    /**
     * Create a new transaction for a specific loan.
     *
     * @param loanId          The ID of the loan for which the transaction is being created.
     * @param transactionDTO  The transaction details.
     * @return Success message.
     */
    @PostMapping("/loan/{loanId}")
    public ResponseEntity<String> createTransaction(
            @PathVariable Long loanId,
            @Valid @RequestBody TransactionDTO transactionDTO) {
        transactionService.createTransaction(loanId, transactionDTO);
        return ResponseEntity.ok("Transaction created successfully for Loan ID: " + loanId);
    }
}

