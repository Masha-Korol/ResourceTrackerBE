package com.example.concert_service.controller;

import com.example.concert_service.data.model.Resource;
import com.example.concert_service.data.dto.resource.ResourceCompanyRecommendation;
import com.example.concert_service.service.RecommendationsService;
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
    public List<Resource> getRecommendationsForUser(){
        return recommendationsService.getRecommendationsForUser();
    }

    @GetMapping("/resource/{id}")
    public List<Resource> getSimilarToResource(@PathVariable Integer id) {
        return recommendationsService.getSimilarToResource(id);
    }

    @GetMapping("/company/{id}")
    public List<ResourceCompanyRecommendation> getRecommendationsByCompany(@PathVariable Integer id){
        return recommendationsService.getRecommendationsByCompany(id);
    }
}
