package com.rewards.repository;

import java.util.List;

import com.rewards.model.Transaction;

public interface TransactionRepository {

	List<Transaction> findByCustomerId(Long customerId);

	List<Transaction> findAll();

	void saveAll(List<Transaction> transactions);
}
