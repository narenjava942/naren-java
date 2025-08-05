package com.example.rewardstest.model;

import java.util.Map;

public class CustomerReward {

        private String customerId;
        private Map<String, Integer> monthlyPoints;
        private int totalPoints;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Map<String, Integer> getMonthlyPoints() {
        return monthlyPoints;
    }

    public void setMonthlyPoints(Map<String, Integer> monthlyPoints) {
        this.monthlyPoints = monthlyPoints;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public CustomerReward(String customerId, Map<String, Integer> monthlyPoints, int totalPoints) {
            this.customerId = customerId;
            this.monthlyPoints = monthlyPoints;
            this.totalPoints = totalPoints;


        }
}
