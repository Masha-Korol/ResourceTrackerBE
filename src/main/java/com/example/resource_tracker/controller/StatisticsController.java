package com.example.resource_tracker.controller;

import com.example.resource_tracker.data.dto.StatActionDto;
import com.example.resource_tracker.data.model.Statistics;
import com.example.resource_tracker.service.StatisticsService;
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
    public List<StatisticsDto> getAll() {
        return statisticsService.getAll();
    }

    @PostMapping("/")
    public void addStat(@RequestBody StatActionDto statActionDto) {
        statisticsService.add(statActionDto);
    }
}
