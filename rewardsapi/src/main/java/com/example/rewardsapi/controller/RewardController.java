package com.example.rewardsapi.controller;


import com.example.rewardsapi.dto.RewardResponseDto;
import com.example.rewardsapi.service.RewardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.List;


@RestController
@RequestMapping("/api/rewards")
public class RewardController {

    private final RewardService rewardService;

    public RewardController(RewardService rewardService) { this.rewardService = rewardService; }

    // GET /api/rewards?start=2025-05&end=2025-07 -> returns rewards for all customers between start and end months
    @GetMapping
    public ResponseEntity<List<RewardResponseDto>> getAll(@RequestParam String start, @RequestParam String end) {
        YearMonth startMonth = YearMonth.parse(start);
        YearMonth endMonth = YearMonth.parse(end);
        return ResponseEntity.ok(rewardService.getAllRewards(startMonth, endMonth));
    }

    // GET /api/rewards/{customerId}?start=2025-05&end=2025-07
    @GetMapping("/{customerId}")
    public ResponseEntity<RewardResponseDto> getForCustomer(@PathVariable String customerId, @RequestParam String start, @RequestParam String end) {
        YearMonth startMonth = YearMonth.parse(start);
        YearMonth endMonth = YearMonth.parse(end);
        return ResponseEntity.ok(rewardService.getRewardsForCustomer(customerId, startMonth, endMonth));
    }
}
