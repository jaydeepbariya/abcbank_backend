package com.abcbank.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "loans")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double loanAmount;

    @Column(nullable = false)
    private Double interestRate;

    @Column(nullable = false)
    private Integer durationMonths; // Duration in months

    @Column(nullable = false)
    private String status; // Example: "PENDING", "APPROVED", "REJECTED"

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
}
