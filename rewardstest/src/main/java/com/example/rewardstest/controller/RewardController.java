package com.example.rewardstest.controller;


import com.example.rewardstest.model.CustomerReward;
import com.example.rewardstest.service.RewardServiceDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rewards")
public class RewardController {

    @Autowired
    private RewardServiceDetails rewardService;

    @GetMapping
    public Map<String, List<CustomerReward>> getRewards() {
        return Map.of("customerRewards", rewardService.calculateRewards());
    }
}