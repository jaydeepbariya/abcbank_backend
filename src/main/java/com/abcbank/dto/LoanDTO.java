package com.abcbank.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanDTO {

    @NotNull(message = "Loan amount is required")
    @Positive(message = "Loan amount must be positive")
    private Double loanAmount;

    @NotNull(message = "Interest rate is required")
    @Positive(message = "Interest rate must be positive")
    private Double interestRate;

    @NotNull(message = "Duration in months is required")
    @Positive(message = "Duration must be a positive integer")
    private Integer durationMonths;

    private String status;
}

