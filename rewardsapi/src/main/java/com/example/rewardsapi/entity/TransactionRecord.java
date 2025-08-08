package com.example.rewardsapi.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class TransactionRecord {
    @Id
    private String id;
    private String customerId;
    private double amount;
    private LocalDate date;

    public TransactionRecord() { this.id = UUID.randomUUID().toString(); }

    public TransactionRecord(String customerId, double amount, LocalDate date) {
        this();
        this.customerId = customerId;
        this.amount = amount;
        this.date = date;
    }

    public String getId() { return id; }
    public String getCustomerId() { return customerId; }
    public double getAmount() { return amount; }
    public LocalDate getDate() { return date; }
}
