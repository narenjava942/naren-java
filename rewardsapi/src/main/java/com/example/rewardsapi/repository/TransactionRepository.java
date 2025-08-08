package com.example.rewardsapi.repository;



import com.example.rewardsapi.entity.TransactionRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionRecord, String> {
    List<TransactionRecord> findByCustomerIdAndDateBetween(String customerId, LocalDate start, LocalDate end);
    List<TransactionRecord> findByDateBetween(LocalDate start, LocalDate end);
}