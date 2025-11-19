package com.rewards.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rewards.model.RewardResponse;
import com.rewards.model.Transaction;
import com.rewards.repository.TransactionRepository;

/**
 * Service that calculates reward points per customer per month and total.
 */
@Service
public class RewardService {

	@Autowired
	private TransactionRepository repo;

	public RewardResponse calculateRewardsByCustomerId(Long customerId) {
        List<Transaction> transactions = repo.findByCustomerId(customerId);
        return calculateRewards(transactions, customerId);
    }

	public List<RewardResponse> getRewardsForAllCustomers() {
        List<Transaction> allTransactions = repo.findAll();

        Map<Long, List<Transaction>> groupedByCustomer = allTransactions.stream()
                .collect(Collectors.groupingBy(Transaction::getCustomerId));

        return groupedByCustomer.entrySet()
                .stream()
                .map(entry -> calculateRewards(entry.getValue(), entry.getKey()))
                .collect(Collectors.toList());
    }

    // 🔧 Reward calculation logic
    private RewardResponse calculateRewards(List<Transaction> transactions, Long customerId) {

        int totalPoints = 0;
        Map<String, Integer> monthlyPoints = new HashMap<>();

        for (Transaction t : transactions) {
            double amount = t.getAmount();
            int points = 0;
            
            if (amount > 100) {
                int amountOver100 = (int) (amount - 100);
                points += amountOver100 * 2;
            }
            
            else  if (amount > 50) {              
                int amountBetween50And100 = (int) (Math.min(amount, 100) - 50);
                points += amountBetween50And100 * 1;
            }
            totalPoints += points;
            String month = t.getDate().getMonth().toString();
            monthlyPoints.put(month, monthlyPoints.getOrDefault(month, 0) + points);
        }

        return new RewardResponse(customerId, monthlyPoints, totalPoints);
    }

}
