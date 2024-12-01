package com.abcbank.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email format")
    private String email;

    @Size(min = 10, max = 15, message = "Phone number should be between 10 to 15 characters")
    private String phone;
}
