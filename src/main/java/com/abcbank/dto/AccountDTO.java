package com.abcbank.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    @NotBlank(message = "Account type is required")
    private String accountType;

    @NotNull(message = "Balance is required")
    @Positive(message = "Balance must be a positive number")
    private Double balance;
}

