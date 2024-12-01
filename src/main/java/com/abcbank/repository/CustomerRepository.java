package com.abcbank.repository;

import com.abcbank.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByUserId(Long userId);
    boolean existsByEmail(String email);
    Optional<Customer> findByUsername(String username);
}
