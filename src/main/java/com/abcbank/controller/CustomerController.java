package com.abcbank.controller;

import com.abcbank.dto.AccountDTO;
import com.abcbank.dto.CustomerDTO;
import com.abcbank.service.impl.CustomerServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@Validated
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerService;

    /**
     * Get customer details by ID.
     *
     * @param customerId The customer ID.
     * @return CustomerDTO with customer details.
     */
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long customerId) {
        CustomerDTO customerDTO = customerService.getCustomerById(customerId);
        return ResponseEntity.ok(customerDTO);
    }

    /**
     * Delete a customer by ID.
     *
     * @param customerId The customer ID.
     * @return Success message.
     */
    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.ok("Customer deleted successfully!");
    }

    /**
     * Get all customers.
     *
     * @return List of CustomerDTO.
     */
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    /**
     * Get accounts for a specific customer.
     *
     * @param customerId The customer ID.
     * @return List of AccountDTO.
     */
    @GetMapping("/{customerId}/accounts")
    public ResponseEntity<List<AccountDTO>> getAccountsByCustomerId(@PathVariable Long customerId) {
        List<AccountDTO> accounts = customerService.getAccountsByCustomerId(customerId);
        return ResponseEntity.ok(accounts);
    }

    /**
     * Assign a new account to a customer.
     *
     * @param customerId  The customer ID.
     * @param accountDTO  The account details.
     * @return Success message.
     */
    @PostMapping("/{customerId}/accounts")
    public ResponseEntity<String> assignAccountToCustomer(
            @PathVariable Long customerId,
            @Valid @RequestBody AccountDTO accountDTO) {
        customerService.assignAccountToCustomer(customerId, accountDTO);
        return ResponseEntity.ok("Account assigned to customer successfully!");
    }
}

