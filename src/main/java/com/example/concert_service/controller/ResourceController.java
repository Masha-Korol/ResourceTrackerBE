package com.example.concert_service.controller;

import com.example.concert_service.data.dto.resource.ResourceDto;
import com.example.concert_service.data.model.Statistics;
import com.example.concert_service.service.ResourceService;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping(path = "/api/v1/resources")
public class ResourceController {

    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @PostMapping("/")
    public void add(@RequestBody ResourceDto artist){
        resourceService.add(artist);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        resourceService.delete(id);
    }

    @GetMapping("/")
    public List<ResourceDto> getAll(){
        return resourceService.getAll();
    }

    @GetMapping("/mark/{id}")
    public void markResource(@PathVariable Integer id, Integer mark) {
        resourceService.markResource(id, mark);
    }

    public List<Statistics> getStatistics() {
        return resourceService.getStatistics
    }
}
