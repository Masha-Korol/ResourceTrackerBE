package com.example.concert_service.controller;

import com.example.concert_service.data.model.Resource;
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

    @GetMapping("/user/{id}")
    public List<Resource> getRecommendationsForUser(@PathVariable Integer id){
        return recommendationsService.getRecommendationsForUser(id);
    }

    @GetMapping("/resource/{id}")
    public List<Resource> getSimilarToResource(@PathVariable Integer id) {
        return recommendationsService.getSimilarToResource(id);
    }
}
