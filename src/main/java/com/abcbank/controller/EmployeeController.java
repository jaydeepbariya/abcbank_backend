package com.abcbank.controller;

import com.abcbank.dto.EmployeeDTO;
import com.abcbank.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Validated
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Get employee details by ID.
     *
     * @param id The employee ID.
     * @return EmployeeDTO with employee details.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employeeDTO);
    }

    /**
     * Update employee details.
     *
     * @param id           The employee ID.
     * @param employeeDTO  The updated employee details.
     * @return Success message.
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeDTO employeeDTO) {
        employeeService.updateEmployee(id, employeeDTO);
        return ResponseEntity.ok("Employee updated successfully!");
    }

    /**
     * Delete an employee by ID.
     *
     * @param id The employee ID.
     * @return Success message.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully!");
    }

    /**
     * Get all employees.
     *
     * @return List of EmployeeDTO.
     */
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    /**
     * Validate or reject a loan.
     *
     * @param loanId      The loan ID.
     * @param isApproved  The approval status.
     * @return Success message.
     */
    @PostMapping("/validate-loan/{loanId}")
    public ResponseEntity<String> validateLoan(
            @PathVariable Long loanId,
            @RequestParam boolean isApproved) {
        employeeService.validateLoan(loanId, isApproved);
        String message = isApproved ? "Loan approved successfully!" : "Loan rejected successfully!";
        return ResponseEntity.ok(message);
    }
}

