package com.abcbank.repository;


import com.abcbank.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByCustomerId(Long customerId);
    boolean existsByIdAndCustomerId(Long accountId, Long customerId);
}