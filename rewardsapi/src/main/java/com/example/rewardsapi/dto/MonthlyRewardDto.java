package com.example.rewardsapi.dto;

public class MonthlyRewardDto {
    private String month; // e.g. 2025-05
    private long points;

    public MonthlyRewardDto() {}
    public MonthlyRewardDto(String month, long points) { this.month = month; this.points = points; }
    public String getMonth() { return month; }
    public long getPoints() { return points; }
    public void setMonth(String month) { this.month = month; }
    public void setPoints(long points) { this.points = points; }
}
