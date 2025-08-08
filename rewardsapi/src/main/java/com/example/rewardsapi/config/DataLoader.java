package com.example.rewardsapi.config;


import com.example.rewardsapi.entity.Customer;
import com.example.rewardsapi.entity.TransactionRecord;
import com.example.rewardsapi.repository.CustomerRepository;
import com.example.rewardsapi.repository.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.time.YearMonth;
import java.util.List;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(CustomerRepository customerRepository, TransactionRepository transactionRepository) {
        return args -> {
            Customer alice = customerRepository.save(new Customer("Alice"));
            Customer bob = customerRepository.save(new Customer("Bob"));

            // Make up transactions across three months: May, June, July 2025
            YearMonth may = YearMonth.of(2025, 5);
            YearMonth june = YearMonth.of(2025, 6);
            YearMonth july = YearMonth.of(2025, 7);

            transactionRepository.saveAll(List.of(
                    new TransactionRecord(alice.getId(), 120.0, may.atDay(15)), // 90 pts
                    new TransactionRecord(alice.getId(), 75.0, may.atDay(23)),  // 25 pts
                    new TransactionRecord(alice.getId(), 200.0, june.atDay(2)), // 250 pts
                    new TransactionRecord(alice.getId(), 10.0, july.atDay(5)),  // 0 pts

                    new TransactionRecord(bob.getId(), 60.0, may.atDay(4)),    // 10 pts
                    new TransactionRecord(bob.getId(), 110.0, june.atDay(17)), // 70 pts
                    new TransactionRecord(bob.getId(), 130.0, july.atDay(29))  // 110 pts
            ));
        };
    }
}