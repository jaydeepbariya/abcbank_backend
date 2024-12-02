package com.abcbank.repository;


import com.abcbank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccountIdOrderByTimestampDesc(Long accountId);
    List<Transaction> findByAccountIdAndTransactionType(Long accountId, String transactionType);
    List<Transaction> findByAccountId(Long accountId);
    List<Transaction> findByLoanId(Long loanId);
}
