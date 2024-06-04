package com.example.concert_service.repository;

import com.example.concert_service.data.model.Statistics;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StatisticsRepository extends PagingAndSortingRepository<Statistics, Integer> {
}
