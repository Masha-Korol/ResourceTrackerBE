package com.example.resource_tracker.service;

import com.example.resource_tracker.data.model.Resource;
import com.example.resource_tracker.data.dto.resource.ResourceCompanyRecommendation;
import com.example.resource_tracker.data.model.Statistics;
import com.example.resource_tracker.data.model.User;
import com.example.resource_tracker.data.model.UserResourceMark;
import com.example.resource_tracker.repository.ResourceRepository;
import com.example.resource_tracker.repository.UserResourceMarkRepository;
import com.example.resource_tracker.service.recommendations.brismf.CFService;
import com.example.resource_tracker.service.recommendations.knn.KNNService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationsService {

    private final CFService cfService;
    private final KNNService knnService;
    private final UserService userService;
    private final StatisticsService statisticsService;
    private final UserResourceMarkRepository userResourceMarkRepository;;

    public RecommendationsService(CFService cfService, KNNService knnService, UserService userService, StatisticsService statisticsService, UserResourceMarkRepository userResourceMarkRepository, ResourceRepository resourceRepository) {
        this.cfService = cfService;
        this.knnService = knnService;
        this.userService = userService;
        this.statisticsService = statisticsService;
        this.userResourceMarkRepository = userResourceMarkRepository;
    }

    public List<Resource> getRecommendationsForUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByLogin(username).get();
        return cfService.getRecommendations(user);
    }

    public List<Resource> getSimilarToResource(Integer id) {
        return knnService.getRecommendations(id);
    }

    public List<ResourceCompanyRecommendation> getRecommendationsByCompany() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByLogin(username).get();

        List<Statistics> companyResourcesByCompany = statisticsService.findCompanyResourcesByCompany(user.getCompany().getId());
        List<UserResourceMark> resourceMarksByUser = userResourceMarkRepository.findResourceMarksByUser(user.getId());
        List<Integer> filteredMarks = resourceMarksByUser.stream().map(userResourceMark -> userResourceMark.getResource().getId()).collect(Collectors.toList());

        List<Statistics> companyResources = companyResourcesByCompany.stream().filter(companyResource -> !filteredMarks.contains(companyResource.getResource().getId())).collect(Collectors.toList());

        List<ResourceCompanyRecommendation> recommendations = new ArrayList<>();
        for (Statistics resource : companyResources) {
            ResourceCompanyRecommendation resourceCompanyRecommendation = new ResourceCompanyRecommendation(resource.getCompany(), resource.getUser().getLogin(), resource.getResource(), resource.getAction(), resource.getMark());
            recommendations.add(resourceCompanyRecommendation);
        }

        return recommendations;
    }
}
