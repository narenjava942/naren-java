package com.example.rewardsapi.controller;


import com.example.rewardsapi.dto.MonthlyRewardDto;
import com.example.rewardsapi.dto.RewardResponseDto;
import com.example.rewardsapi.service.RewardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;


import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.YearMonth;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RewardController.class)
class RewardControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockitoBean // New recommended annotation instead of @MockBean
    private RewardService rewardService;

    private RewardResponseDto sampleResponse;


    @BeforeEach
    void setUp() {
        List<MonthlyRewardDto> monthlyPoints = Arrays.asList(
                new MonthlyRewardDto("2025-05", 120),
                new MonthlyRewardDto("2025-06", 90),
                new MonthlyRewardDto("2025-07", 150)
        );

        sampleResponse = new RewardResponseDto(
                "CUST001",
                "John Doe",
                monthlyPoints,
                360
        );
    }

    @Test
    void testGetAllRewards() throws Exception {
        Mockito.when(rewardService.getAllRewards(
                YearMonth.of(2025, 5),
                YearMonth.of(2025, 7)
        )).thenReturn(List.of(sampleResponse));

        mockMvc.perform(get("/api/rewards")
                        .param("start", "2025-05")
                        .param("end", "2025-07")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].customerId", is("CUST001")))
                .andExpect(jsonPath("$[0].customerName", is("John Doe")))
                .andExpect(jsonPath("$[0].totalPoints", is(360)));
    }


}

