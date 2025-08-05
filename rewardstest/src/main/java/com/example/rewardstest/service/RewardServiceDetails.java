package com.example.rewardstest.service;


import com.example.rewardstest.model.CustomerReward;
import com.example.rewardstest.model.TransData;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

@Service
public class RewardServiceDetails {

    public List<TransData> getMockTransactions() {
        return List.of(
                new TransData("CUST1001", LocalDate.of(2025, 6, 10), 120),
                new TransData("CUST1001", LocalDate.of(2025, 7, 5), 90),
                new TransData("CUST1001", LocalDate.of(2025, 8, 1), 75),
                new TransData("CUST1002", LocalDate.of(2025, 7, 15), 130),
                new TransData("CUST1002", LocalDate.of(2025, 8, 4), 95)
        );
    }

    public int calculatePoints(double amount) {
        int points = 0;
        if (amount > 100) {
            points += (int)((amount - 100) * 2);
            points += 50; // $50 between 50 and 100 = 50 points
        } else if (amount > 50) {
            points += (int)(amount - 50);
        }
        return points;
    }

    public List<CustomerReward> calculateRewards() {
        Map<String, Map<YearMonth, Integer>> customerMonthlyPoints = new HashMap<>();

        for (TransData txn : getMockTransactions()) {
            int points = calculatePoints(txn.getAmount());
            String customerId = txn.getCustomerId();
            YearMonth month = YearMonth.from(txn.getDate());

            customerMonthlyPoints
                    .computeIfAbsent(customerId, k -> new HashMap<>())
                    .merge(month, points, Integer::sum);
        }

        List<CustomerReward> rewards = new ArrayList<>();
        for (var entry : customerMonthlyPoints.entrySet()) {
            String customerId = entry.getKey();
            Map<String, Integer> monthly = new TreeMap<>();
            int total = 0;
            for (var m : entry.getValue().entrySet()) {
                monthly.put(m.getKey().toString(), m.getValue());
                total += m.getValue();
            }
            rewards.add(new CustomerReward(customerId, monthly, total));
        }

        return rewards;
    }


}
