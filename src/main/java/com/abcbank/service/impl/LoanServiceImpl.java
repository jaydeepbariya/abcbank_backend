package com.abcbank.service.impl;

import com.abcbank.dto.LoanDTO;
import com.abcbank.dto.TransactionDTO;
import com.abcbank.entity.Loan;
import com.abcbank.entity.Customer;
import com.abcbank.entity.Transaction;
import com.abcbank.exception.LoanException;
import com.abcbank.repository.LoanRepository;
import com.abcbank.repository.CustomerRepository;
import com.abcbank.repository.TransactionRepository;
import com.abcbank.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public LoanServiceImpl(LoanRepository loanRepository, CustomerRepository customerRepository, TransactionRepository transactionRepository) {
        this.loanRepository = loanRepository;
        this.customerRepository = customerRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public LoanDTO getLoanById(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanException("Loan not found with ID: " + loanId));
        return mapToDTO(loan);
    }

    @Override
    public void createLoan(Long customerId, LoanDTO loanDTO) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new LoanException("Customer not found with ID: " + customerId));

        Loan loan = new Loan();
        loan.setStatus(loanDTO.getStatus());
        loan.setLoanAmount(loanDTO.getLoanAmount());
        loan.setInterestRate(loanDTO.getInterestRate());
        loan.setDurationMonths(loanDTO.getDurationMonths());

        loanRepository.save(loan);
    }

    @Override
    public void updateLoan(Long loanId, LoanDTO loanDTO) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanException("Loan not found with ID: " + loanId));

        loan.setStatus(loanDTO.getStatus());
        loan.setLoanAmount(loanDTO.getLoanAmount());
        loan.setInterestRate(loanDTO.getInterestRate());
        loan.setDurationMonths(loanDTO.getDurationMonths());

        loanRepository.save(loan);
    }

    @Override
    public void deleteLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanException("Loan not found with ID: " + loanId));

        loanRepository.delete(loan);
    }

    @Override
    public List<LoanDTO> getAllLoans() {
        return loanRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void repayLoan(Long loanId, Double amount) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanException("Loan not found with ID: " + loanId));

        if (loan.getLoanAmount() < amount) {
            throw new LoanException("Repayment amount exceeds loan amount");
        }

        loan.setLoanAmount(loan.getLoanAmount() - amount);
        loanRepository.save(loan);

        // Create a repayment transaction
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTransactionType("REPAYMENT");

        transactionRepository.save(transaction);
    }

    private LoanDTO mapToDTO(Loan loan) {
        return new LoanDTO(
                loan.getLoanAmount(),
                loan.getInterestRate(),
                loan.getDurationMonths(),
                loan.getStatus());
    }
}
