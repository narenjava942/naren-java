package com.example.rewardstest.service;


import com.example.rewardstest.model.CustomerReward;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RewardServiceDetailsTest {
    private final RewardServiceDetails rewardService = new RewardServiceDetails();

    @Test
    void testCalculatePointsBelow50() {
        assertEquals(0, rewardService.calculatePoints(45));
    }

    @Test
    void testCalculatePointsBetween50And100() {
        assertEquals(30, rewardService.calculatePoints(80)); // 80-50 = 30 points
    }

    @Test
    void testCalculatePointsAbove100() {
        assertEquals(90, rewardService.calculatePoints(120)); // 2*20 + 50
    }

    @Test
    void testCalculateRewards() {
        List<CustomerReward> rewards = rewardService.calculateRewards();

        assertFalse(rewards.isEmpty());
        CustomerReward cust1 = rewards.stream()
                .filter(r -> r.getCustomerId().equals("CUST1001"))
                .findFirst().orElse(null);

        assertNotNull(cust1);
        assertTrue(cust1.getMonthlyPoints().containsKey("2025-06"));
        assertEquals(90, cust1.getMonthlyPoints().get("2025-06")); // for $120 transaction
        assertEquals(155, cust1.getTotalPoints());
    }
}
