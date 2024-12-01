package com.abcbank.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    @NotBlank(message = "Transaction type is required")
    private String transactionType;

    @NotNull(message = "Transaction amount is required")
    @Positive(message = "Amount must be positive")
    private Double amount;

    private LocalDateTime timestamp;
}
