package com.abcbank.service.impl;

import com.abcbank.dto.TransactionDTO;
import com.abcbank.entity.Loan;
import com.abcbank.entity.Transaction;
import com.abcbank.exception.TransactionException;
import com.abcbank.repository.TransactionRepository;
import com.abcbank.repository.LoanRepository;
import com.abcbank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final LoanRepository loanRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, LoanRepository loanRepository) {
        this.transactionRepository = transactionRepository;
        this.loanRepository = loanRepository;
    }

    @Override
    public TransactionDTO getTransactionById(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionException("Transaction not found with ID: " + transactionId));
        return mapToDTO(transaction);
    }

    @Override
    public List<TransactionDTO> getAllTransactions() {
        return transactionRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void createTransaction(Long loanId, TransactionDTO transactionDTO) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new TransactionException("Loan not found with ID: " + loanId));

        Transaction transaction = new Transaction();
        transaction.setTransactionType(transactionDTO.getTransactionType());
        transaction.setAmount(transactionDTO.getAmount());

        transactionRepository.save(transaction);
    }

    private TransactionDTO mapToDTO(Transaction transaction) {
        return new TransactionDTO(
                transaction.getTransactionType(),
                transaction.getAmount(),
                transaction.getTimestamp());
    }
}

