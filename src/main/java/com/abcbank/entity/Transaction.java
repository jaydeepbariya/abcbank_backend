package com.abcbank.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String transactionType; // Example: "DEPOSIT", "WITHDRAWAL"

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
}

