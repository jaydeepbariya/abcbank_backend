package com.abcbank.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanValidationDTO {

    @NotNull(message = "Loan ID is required")
    private Long loanId;

    @NotBlank(message = "Status is required")
    private String status;
}

