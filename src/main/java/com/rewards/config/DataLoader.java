package com.rewards.config;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rewards.model.Transaction;
import com.rewards.repository.TransactionRepository;

@Configuration
public class DataLoader {
	 @Bean
	    CommandLineRunner loadMockData(TransactionRepository repo) {

	        return (args) -> {

	            repo.saveAll(Arrays.asList(
	                    new Transaction(1L, 120.0, LocalDate.of(2024, 1, 10)),
	                    new Transaction(1L, 75.0, LocalDate.of(2024, 2, 15)),
	                    new Transaction(1L, 200.0, LocalDate.of(2024, 3, 20)),

	                    new Transaction(2L, 60.0, LocalDate.of(2024, 1, 5)),
	                    new Transaction(2L, 110.0, LocalDate.of(2024, 1, 25)),

	                    new Transaction(3L, 130.0, LocalDate.of(2024, 3, 18))
	            ));

	            System.out.println("data loaded successfully.");
	        };
	    }
}
