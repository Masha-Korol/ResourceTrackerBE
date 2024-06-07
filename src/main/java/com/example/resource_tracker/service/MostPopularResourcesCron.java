package com.example.resource_tracker.service;

import com.example.resource_tracker.data.model.Resource;
import com.example.resource_tracker.data.model.Statistics;
import com.example.resource_tracker.repository.ResourceRepository;
import com.example.resource_tracker.repository.StatisticsRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MostPopularResourcesCron {

    private final ResourceRepository resourceRepository;
    private final StatisticsRepository statisticsRepository;

    public MostPopularResourcesCron(ResourceRepository resourceRepository, StatisticsRepository statisticsRepository) {
        this.resourceRepository = resourceRepository;
        this.statisticsRepository = statisticsRepository;
    }

    @Scheduled(cron = "0 0 0 * * MON") // Every Monday at midnight
    public void findMostPopularResources() {
        ArrayList<Statistics> stats = (ArrayList<Statistics>) statisticsRepository.findAll();

    }
}
