package com.example.resource_tracker.service;

import com.example.resource_tracker.data.dto.StatActionDto;
import com.example.resource_tracker.data.model.Resource;
import com.example.resource_tracker.data.model.Statistics;
import com.example.resource_tracker.data.model.User;
import com.example.resource_tracker.repository.ResourceRepository;
import com.example.resource_tracker.repository.StatisticsRepository;
import com.example.resource_tracker.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StatisticsService {

    private final StatisticsRepository statisticsRepository;
    private final ResourceRepository resourceRepository;
    private final UserRepository userRepository;

    public StatisticsService(StatisticsRepository statisticsRepository, ResourceRepository resourceRepository, UserRepository userRepository) {
        this.statisticsRepository = statisticsRepository;
        this.resourceRepository = resourceRepository;
        this.userRepository = userRepository;
    }

    public List<StatisticsDto> getAll() {
        return (List<StatisticsDto>) statisticsRepository.findAll();
    }

    public void add(StatActionDto statActionDto) {
        Resource resource = resourceRepository.findById(statActionDto.getResourceId()).get();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByLogin(username).get();

        Statistics statistics = new Statistics(new Date().getTime(), statActionDto.getAction(), user, resource);
        statisticsRepository.save(statistics);
    }
}
