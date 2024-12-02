package com.abcbank.service;

import com.abcbank.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO getEmployeeById(Long id);

    void updateEmployee(Long id, EmployeeDTO employeeDTO);

    void deleteEmployee(Long id);

    List<EmployeeDTO> getAllEmployees();

    void validateLoan(Long loanId, boolean isApproved);
}

