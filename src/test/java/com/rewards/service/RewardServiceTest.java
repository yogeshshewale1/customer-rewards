package com.rewards.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.rewards.model.RewardResponse;
import com.rewards.model.Transaction;
import com.rewards.repository.TransactionRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class RewardServiceTest {

    @Mock
    private TransactionRepository repo;

    @InjectMocks
    private RewardService rewardService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculateRewardsByCustomerId() {
        List<Transaction> transactions = Arrays.asList(
                new Transaction(1L, 120.0, LocalDate.of(2025, 1, 15)),
                new Transaction(1L, 75.0, LocalDate.of(2025, 2, 20)),
                new Transaction(1L, 40.0, LocalDate.of(2025, 3, 5))
        );

        when(repo.findByCustomerId(1L)).thenReturn(transactions);

        RewardResponse response = rewardService.calculateRewardsByCustomerId(1L);

        assertEquals(1L, response.getCustomerId());
        assertEquals(65, response.getTotalPoints()); // 2*(120-100)=40 + 75-50=25 => 65
        assertEquals(3, response.getMonthlyPoints().size());
        assertEquals(40, response.getMonthlyPoints().get("JANUARY"));
        assertEquals(25, response.getMonthlyPoints().get("FEBRUARY"));
        assertEquals(0, response.getMonthlyPoints().get("MARCH"));
    }

    @Test
    void testGetRewardsForAllCustomers() {
        List<Transaction> allTransactions = Arrays.asList(
                new Transaction(1L, 120.0, LocalDate.of(2025, 1, 15)),
                new Transaction(2L, 200.0, LocalDate.of(2025, 1, 20))
        );

        when(repo.findAll()).thenReturn(allTransactions);

        List<RewardResponse> responses = rewardService.getRewardsForAllCustomers();

        assertEquals(2, responses.size());
    }
}
