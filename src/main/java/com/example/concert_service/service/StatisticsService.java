package com.example.concert_service.service;

import com.example.concert_service.data.dto.StatActionDto;
import com.example.concert_service.data.model.Resource;
import com.example.concert_service.data.model.Statistics;
import com.example.concert_service.data.model.User;
import com.example.concert_service.repository.ResourceRepository;
import com.example.concert_service.repository.StatisticsRepository;
import com.example.concert_service.repository.UserRepository;
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

    public List<Statistics> getAll() {
        return (List<Statistics>) statisticsRepository.findAll();
    }

    public void add(StatActionDto statActionDto) {
        Resource resource = resourceRepository.findById(statActionDto.getResourceId()).get();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByLogin(username).get();

        Statistics statistics = new Statistics(new Date().getTime(), statActionDto.getAction(), user, resource);
        statisticsRepository.save(statistics);
    }
}
