package com.example.concert_service.controller;

import com.example.concert_service.data.dto.StatActionDto;
import com.example.concert_service.data.model.Statistics;
import com.example.concert_service.service.StatisticsService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/")
    public List<Statistics> getAll() {
        return statisticsService.getAll();
    }

    @PostMapping("/")
    public void addStat(@RequestBody StatActionDto statActionDto) {
        statisticsService.add(statActionDto);
    }
}
