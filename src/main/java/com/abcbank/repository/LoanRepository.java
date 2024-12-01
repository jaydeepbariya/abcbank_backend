package com.abcbank.repository;

import com.abcbank.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByAccountId(Long accountId);
    List<Loan> findByStatus(String status);
    long countByStatus(String status);

    @Query("SELECT l FROM Loan l WHERE l.account.id = :accountId AND l.status = 'PENDING'")
    List<Loan> findPendingLoansByAccountId(Long accountId);
}
