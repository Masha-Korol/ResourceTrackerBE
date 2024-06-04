package com.example.resource_tracker.controller;

import com.example.resource_tracker.data.model.Resource;
import com.example.resource_tracker.data.dto.resource.ResourceCompanyRecommendation;
import com.example.resource_tracker.service.RecommendationsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/recommendations")
public class RecommendationsController {

    private final RecommendationsService recommendationsService;

    public RecommendationsController(RecommendationsService recommendationsService) {
        this.recommendationsService = recommendationsService;
    }

    @GetMapping("/user")
    public List<ResourceDto> getRecommendationsForUser(){
        return recommendationsService.getRecommendationsForUser();
    }

    @GetMapping("/resource/{id}")
    public List<ResourceDto> getSimilarToResource(@PathVariable Integer id) {
        return recommendationsService.getSimilarToResource(id);
    }

    @GetMapping("/company")
    public List<ResourceCompanyRecommendationDto> getRecommendationsByCompany() {
        return recommendationsService.getRecommendationsByCompany();
    }
}
