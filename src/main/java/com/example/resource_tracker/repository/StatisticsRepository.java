package com.example.resource_tracker.repository;

import com.example.resource_tracker.data.model.Statistics;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StatisticsRepository extends PagingAndSortingRepository<Statistics, Integer> {
}
