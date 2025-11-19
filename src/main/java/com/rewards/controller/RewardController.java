package com.rewards.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rewards.model.RewardResponse;
import com.rewards.service.RewardService;

/**
 * REST controller exposing rewards calculation endpoint.
 */
@RestController
@RequestMapping("/api/rewards")
@Validated
public class RewardController {

	@Autowired
	private RewardService rewardService;
    
	@GetMapping("/{customerId}")
    public RewardResponse getRewardPoints(@PathVariable Long customerId) {
        return rewardService.calculateRewardsByCustomerId(customerId);
    }
	
	@GetMapping("/all")
    public ResponseEntity<List<RewardResponse>> getRewardsForAllCustomers() {
        List<RewardResponse> rewards = rewardService.getRewardsForAllCustomers();
        return ResponseEntity.ok(rewards);
    }
}
