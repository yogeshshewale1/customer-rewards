package com.rewards.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.rewards.model.Transaction;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

    private final List<Transaction> data = new ArrayList<>();

    @Override
    public List<Transaction> findByCustomerId(Long customerId) {
        return data.stream()
                .filter(t -> t.getCustomerId().equals(customerId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaction> findAll() {
        return data;
    }

    @Override
    public void saveAll(List<Transaction> transactions) {
        data.addAll(transactions);
    }
}
