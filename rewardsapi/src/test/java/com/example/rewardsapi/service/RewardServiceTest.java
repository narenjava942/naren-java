package com.example.rewardsapi.service;


import com.example.rewardsapi.entity.Customer;
import com.example.rewardsapi.entity.TransactionRecord;
import com.example.rewardsapi.repository.CustomerRepository;
import com.example.rewardsapi.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class RewardServiceTest {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    TransactionRepository transactionRepository;

    RewardService rewardService;

    Customer c;

    @BeforeEach
    void setup() {
        rewardService = new RewardService(customerRepository, transactionRepository);
        c = customerRepository.save(new Customer("TestUser"));
        transactionRepository.save(new TransactionRecord(c.getId(), 120.0, LocalDate.of(2025,5,10)));
        transactionRepository.save(new TransactionRecord(c.getId(), 80.0, LocalDate.of(2025,6,11)));
    }

    @Test
    void testCalculatePointsForAmount() {
        assertEquals(90, rewardService.calculatePointsForAmount(120.0));
        assertEquals(25, rewardService.calculatePointsForAmount(75.0));
        assertEquals(0, rewardService.calculatePointsForAmount(30.0));
    }

    @Test
    void testGetRewardsForCustomer() {
        var resp = rewardService.getRewardsForCustomer(c.getId(), YearMonth.of(2025,5), YearMonth.of(2025,6));
        assertEquals(2, resp.getMonthly().size());
        assertEquals(120, resp.getTotalPoints());
    }
}
