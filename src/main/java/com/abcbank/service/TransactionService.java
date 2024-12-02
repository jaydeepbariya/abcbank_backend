package com.abcbank.service;

import com.abcbank.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {

    TransactionDTO getTransactionById(Long transactionId);

    List<TransactionDTO> getAllTransactions();

    void createTransaction(Long loanId, TransactionDTO transactionDTO);
}

