package com.abcbank.controller;

import com.abcbank.dto.LoanDTO;
import com.abcbank.dto.TransactionDTO;
import com.abcbank.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@Validated
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    /**
     * Get loan details by ID.
     *
     * @param loanId The loan ID.
     * @return LoanDTO containing loan details.
     */
    @GetMapping("/{loanId}")
    public ResponseEntity<LoanDTO> getLoanById(@PathVariable Long loanId) {
        LoanDTO loanDTO = loanService.getLoanById(loanId);
        return ResponseEntity.ok(loanDTO);
    }

    /**
     * Create a new loan for a customer.
     *
     * @param customerId The customer ID.
     * @param loanDTO    The loan details.
     * @return Success message.
     */
    @PostMapping("/customers/{customerId}")
    public ResponseEntity<String> createLoan(@PathVariable Long customerId, @Valid @RequestBody LoanDTO loanDTO) {
        loanService.createLoan(customerId, loanDTO);
        return ResponseEntity.ok("Loan created successfully!");
    }

    /**
     * Update an existing loan.
     *
     * @param loanId  The loan ID.
     * @param loanDTO The updated loan details.
     * @return Success message.
     */
    @PutMapping("/{loanId}")
    public ResponseEntity<String> updateLoan(@PathVariable Long loanId, @Valid @RequestBody LoanDTO loanDTO) {
        loanService.updateLoan(loanId, loanDTO);
        return ResponseEntity.ok("Loan updated successfully!");
    }

    /**
     * Delete a loan by ID.
     *
     * @param loanId The loan ID.
     * @return Success message.
     */
    @DeleteMapping("/{loanId}")
    public ResponseEntity<String> deleteLoan(@PathVariable Long loanId) {
        loanService.deleteLoan(loanId);
        return ResponseEntity.ok("Loan deleted successfully!");
    }

    /**
     * Get a list of all loans.
     *
     * @return List of LoanDTO.
     */
    @GetMapping
    public ResponseEntity<List<LoanDTO>> getAllLoans() {
        List<LoanDTO> loans = loanService.getAllLoans();
        return ResponseEntity.ok(loans);
    }

    /**
     * Repay a loan.
     *
     * @param loanId The loan ID.
     * @param amount The repayment amount.
     * @return Success message.
     */
    @PostMapping("/{loanId}/repay")
    public ResponseEntity<String> repayLoan(@PathVariable Long loanId, @RequestParam Double amount) {
        loanService.repayLoan(loanId, amount);
        return ResponseEntity.ok("Loan repayment successful!");
    }

}

