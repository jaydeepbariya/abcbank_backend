package com.abcbank.service.impl;

import com.abcbank.dto.EmployeeDTO;
import com.abcbank.dto.LoanDTO;
import com.abcbank.entity.Employee;
import com.abcbank.entity.Loan;
import com.abcbank.exception.EmployeeException;
import com.abcbank.repository.EmployeeRepository;
import com.abcbank.repository.LoanRepository;
import com.abcbank.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final LoanRepository loanRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, LoanRepository loanRepository) {
        this.employeeRepository = employeeRepository;
        this.loanRepository = loanRepository;
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeException("Employee not found with ID: " + id));
        return mapToDTO(employee);
    }

    @Override
    public void updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeException("Employee not found with ID: " + id));

        employee.setDesignation(employeeDTO.getDesignation());

        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeException("Employee not found with ID: " + id);
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void validateLoan(Long loanId, boolean isApproved) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new EmployeeException("Loan not found with ID: " + loanId));

        loan.setStatus("APPROVED");
        loanRepository.save(loan);
    }

    private EmployeeDTO mapToDTO(Employee employee) {
        return new EmployeeDTO(
                employee.getDesignation()
        );
    }
}

