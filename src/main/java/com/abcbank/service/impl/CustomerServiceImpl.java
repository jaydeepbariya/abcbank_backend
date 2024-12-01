package com.abcbank.service.impl;

import com.abcbank.dto.AccountDTO;
import com.abcbank.dto.CustomerDTO;
import com.abcbank.entity.Account;
import com.abcbank.entity.Customer;
import com.abcbank.exception.CustomerException;
import com.abcbank.repository.AccountRepository;
import com.abcbank.repository.CustomerRepository;
import com.abcbank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerException("Customer not found with ID: " + id));
        return mapToDTO(customer);
    }

    @Override
    public CustomerDTO getCustomerByUsername(String username) {
        Customer customer = customerRepository.findByUsername(username)
                .orElseThrow(() -> new CustomerException("Customer not found with username: " + username));
        return mapToDTO(customer);
    }

    @Override
    public void updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerException("Customer not found with ID: " + id));

        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhone(customerDTO.getPhone());

        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerException("Customer not found with ID: " + id);
        }
        customerRepository.deleteById(id);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AccountDTO> getAccountsByCustomerId(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerException("Customer not found with ID: " + customerId));

        return customer.getAccounts().stream()
                .map(account -> new AccountDTO(account.getAccountType(), account.getBalance()))
                .collect(Collectors.toList());
    }

    @Override
    public void assignAccountToCustomer(Long customerId, AccountDTO accountDTO) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerException("Customer not found with ID: " + customerId));

        Account account = new Account();
        account.setAccountType(accountDTO.getAccountType());
        account.setBalance(accountDTO.getBalance());
        account.setCustomer(customer);

        accountRepository.save(account);
    }

    private CustomerDTO mapToDTO(Customer customer) {
        return new CustomerDTO(
                customer.getName(),
                customer.getEmail(),
                customer.getPhone()
        );
    }
}
