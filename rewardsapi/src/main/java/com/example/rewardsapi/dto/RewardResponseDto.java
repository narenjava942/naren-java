package com.example.rewardsapi.dto;

import java.util.List;

public class RewardResponseDto {
    private String customerId;
    private String customerName;
    private List<MonthlyRewardDto> monthly;
    private long totalPoints;

    public RewardResponseDto() {}

    public RewardResponseDto(String customerId, String customerName, List<MonthlyRewardDto> monthly, long totalPoints) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.monthly = monthly;
        this.totalPoints = totalPoints;
    }

    public String getCustomerId() { return customerId; }
    public String getCustomerName() { return customerName; }
    public List<MonthlyRewardDto> getMonthly() { return monthly; }
    public long getTotalPoints() { return totalPoints; }
}
