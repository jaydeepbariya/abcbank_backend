package com.abcbank.service.impl;

import com.abcbank.dto.AccountDTO;
import com.abcbank.dto.TransactionDTO;
import com.abcbank.entity.Account;
import com.abcbank.entity.Customer;
import com.abcbank.entity.Transaction;
import com.abcbank.exception.AccountException;
import com.abcbank.repository.AccountRepository;
import com.abcbank.repository.CustomerRepository;
import com.abcbank.repository.TransactionRepository;
import com.abcbank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, CustomerRepository customerRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public AccountDTO getAccountById(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountException("Account not found with ID: " + accountId));
        return mapToDTO(account);
    }

    @Override
    public void createAccount(Long customerId, AccountDTO accountDTO) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new AccountException("Customer not found with ID: " + customerId));

        Account account = new Account();
        account.setAccountType(accountDTO.getAccountType());
        account.setBalance(accountDTO.getBalance());
        account.setCustomer(customer);

        accountRepository.save(account);
    }

    @Override
    public void updateAccount(Long accountId, AccountDTO accountDTO) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountException("Account not found with ID: " + accountId));

        account.setAccountType(accountDTO.getAccountType());
        account.setBalance(accountDTO.getBalance());

        accountRepository.save(account);
    }

    @Override
    public void deleteAccount(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountException("Account not found with ID: " + accountId));

        accountRepository.delete(account);
    }

    @Override
    public List<AccountDTO> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void depositToAccount(Long accountId, Double amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountException("Account not found with ID: " + accountId));

        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);

        // Create a deposit transaction
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setTransactionType("DEPOSIT");
        transactionRepository.save(transaction);
    }

    @Override
    public void withdrawFromAccount(Long accountId, Double amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountException("Account not found with ID: " + accountId));

        if (account.getBalance() < amount) {
            throw new AccountException("Insufficient funds");
        }

        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);

        // Create a withdrawal transaction
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setTransactionType("WITHDRAWAL");

        transactionRepository.save(transaction);
    }

    @Override
    public List<TransactionDTO> getTransactionsByAccountId(Long accountId) {
        List<Transaction> transactions = transactionRepository.findByAccountId(accountId);

        return transactions.stream()
                .map(transaction -> new TransactionDTO(
                        transaction.getTransactionType(),
                        transaction.getAmount(),
                        transaction.getTimestamp()))
                .collect(Collectors.toList());
    }

    private AccountDTO mapToDTO(Account account) {
        return new AccountDTO(
                account.getAccountType(),
                account.getBalance());
    }
}

