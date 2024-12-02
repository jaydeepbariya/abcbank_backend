package com.abcbank.service;
import com.abcbank.dto.LoanDTO;
import com.abcbank.dto.TransactionDTO;

import java.util.List;

public interface LoanService {

    LoanDTO getLoanById(Long loanId);

    void createLoan(Long customerId, LoanDTO loanDTO);

    void updateLoan(Long loanId, LoanDTO loanDTO);

    void deleteLoan(Long loanId);

    List<LoanDTO> getAllLoans();

    void repayLoan(Long loanId, Double amount);

}

