package com.example.concert_service.service;

import com.example.concert_service.data.model.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationsService {

    public List<Resource> getRecommendationsForUser(Integer id) {
        return new ArrayList<>();
    }

    public List<Resource> getSimilarToResource(Integer id) {
        return new ArrayList<>();
    }
}
