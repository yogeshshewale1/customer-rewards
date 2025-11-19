package com.rewards.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class RewardControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testGetRewardsForCustomer() throws Exception {
		mockMvc.perform(get("/api/rewards/1")).andExpect(status().isOk()).andExpect(jsonPath("$.customerId").value(1))
				.andExpect(jsonPath("$.totalPoints").exists()).andExpect(jsonPath("$.monthlyPoints").exists());
	}

	@Test
	void testGetAllCustomerRewards() throws Exception {
		mockMvc.perform(get("/api/rewards/all")).andExpect(status().isOk()).andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$[0].customerId").exists()).andExpect(jsonPath("$[0].totalPoints").exists());
	}

	@Test
	void testGetRewardsForInvalidCustomer() throws Exception {
		mockMvc.perform(get("/api/rewards/999")).andExpect(status().isOk())
				.andExpect(jsonPath("$.customerId").value(999));
	}

}
