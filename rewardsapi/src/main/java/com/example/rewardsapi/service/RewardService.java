package com.example.rewardsapi.service;


import com.example.rewardsapi.dto.MonthlyRewardDto;
import com.example.rewardsapi.dto.RewardResponseDto;
import com.example.rewardsapi.entity.Customer;
import com.example.rewardsapi.entity.TransactionRecord;
import com.example.rewardsapi.exception.NotFoundException;
import com.example.rewardsapi.repository.CustomerRepository;
import com.example.rewardsapi.repository.TransactionRepository;


import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RewardService {

    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;

    public RewardService(CustomerRepository customerRepository, TransactionRepository transactionRepository) {
        this.customerRepository = customerRepository;
        this.transactionRepository = transactionRepository;
    }

    public long calculatePointsForAmount(double amount) {
        long points = 0;
        if (amount > 100) {
            points += Math.round((amount - 100) * 2);
            points += 50; // for the $50-$100 => 1 point per dollar
        } else if (amount > 50) {
            points += Math.round((amount - 50) * 1);
        }
        return points;
    }

    public RewardResponseDto getRewardsForCustomer(String customerId, YearMonth startMonth, YearMonth endMonth) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (customerOpt.isEmpty()) throw new NotFoundException("Customer not found: " + customerId);
        Customer customer = customerOpt.get();

        LocalDate start = startMonth.atDay(1);
        LocalDate end = endMonth.atEndOfMonth();

        List<TransactionRecord> transactions = transactionRepository.findByCustomerIdAndDateBetween(customerId, start, end);

        Map<YearMonth, Long> map = new TreeMap<>();

        for (TransactionRecord t : transactions) {
            YearMonth ym = YearMonth.from(t.getDate());
            long pts = calculatePointsForAmount(t.getAmount());
            map.put(ym, map.getOrDefault(ym, 0L) + pts);
        }

        List<MonthlyRewardDto> monthly = map.entrySet().stream()
                .map(e -> new MonthlyRewardDto(e.getKey().toString(), e.getValue()))
                .collect(Collectors.toList());

        long total = monthly.stream().mapToLong(MonthlyRewardDto::getPoints).sum();

        return new RewardResponseDto(customer.getId(), customer.getName(), monthly, total);
    }

    public List<RewardResponseDto> getAllRewards(YearMonth startMonth, YearMonth endMonth) {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(c -> getRewardsForCustomer(c.getId(), startMonth, endMonth))
                .collect(Collectors.toList());
    }
}
