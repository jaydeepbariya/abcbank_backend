package com.abcbank.service;

import com.abcbank.dto.AccountDTO;
import com.abcbank.dto.TransactionDTO;

import java.util.List;

public interface AccountService {

    AccountDTO getAccountById(Long accountId);

    void createAccount(Long customerId, AccountDTO accountDTO);

    void updateAccount(Long accountId, AccountDTO accountDTO);

    void deleteAccount(Long accountId);

    List<AccountDTO> getAllAccounts();

    void depositToAccount(Long accountId, Double amount);

    void withdrawFromAccount(Long accountId, Double amount);

    List<TransactionDTO> getTransactionsByAccountId(Long accountId);
}

