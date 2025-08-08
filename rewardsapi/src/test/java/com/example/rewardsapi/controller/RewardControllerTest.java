package com.example.rewardsapi.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RewardControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void testGetAllRewards() throws Exception {
        mockMvc.perform(get("/api/rewards?start=2025-05&end=2025-07")).andExpect(status().isOk());
    }
}