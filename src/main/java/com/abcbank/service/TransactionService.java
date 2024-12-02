package com.abcbank.service;

import com.abcbank.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {

    TransactionDTO getTransactionById(Long transactionId);

    List<TransactionDTO> getAllTransactions();

    List<TransactionDTO> getTransactionsByLoanId(Long loanId);

    void createTransaction(Long loanId, TransactionDTO transactionDTO);
}

