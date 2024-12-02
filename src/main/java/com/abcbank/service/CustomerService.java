package com.abcbank.service;

import com.abcbank.dto.CustomerDTO;
import com.abcbank.dto.AccountDTO;

import java.util.List;

public interface CustomerService {

    CustomerDTO getCustomerById(Long id);

    void deleteCustomer(Long id);

    List<CustomerDTO> getAllCustomers();

    List<AccountDTO> getAccountsByCustomerId(Long customerId);

    void assignAccountToCustomer(Long customerId, AccountDTO accountDTO);
}
