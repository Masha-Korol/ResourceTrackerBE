package com.example.concert_service.service;

import com.example.concert_service.data.model.CompanyResource;
import com.example.concert_service.data.model.Resource;
import com.example.concert_service.data.dto.resource.ResourceCompanyRecommendation;
import com.example.concert_service.data.model.User;
import com.example.concert_service.data.model.UserResourceMark;
import com.example.concert_service.repository.CompanyResourceRepository;
import com.example.concert_service.repository.ResourceRepository;
import com.example.concert_service.repository.UserResourceMarkRepository;
import com.example.concert_service.service.recommendations.CFService;
import com.example.concert_service.service.recommendations.KNNService;
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
    private final CompanyResourceRepository companyResourceRepository;
    private final UserResourceMarkRepository userResourceMarkRepository;;

    public RecommendationsService(CFService cfService, KNNService knnService, UserService userService, CompanyResourceRepository companyResourceRepository, UserResourceMarkRepository userResourceMarkRepository, ResourceRepository resourceRepository) {
        this.cfService = cfService;
        this.knnService = knnService;
        this.userService = userService;
        this.companyResourceRepository = companyResourceRepository;
        this.userResourceMarkRepository = userResourceMarkRepository;
    }

    public List<Resource> getRecommendationsForUser() {
        return cfService.getRecommendations();
    }

    public List<Resource> getSimilarToResource(Integer id) {
        return knnService.getRecommendations(id);
    }

    public List<ResourceCompanyRecommendation> getRecommendationsByCompany(Integer id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByLogin(username).get();

        List<CompanyResource> companyResourcesByCompany = companyResourceRepository.findCompanyResourcesByCompany(user.getCompany().getCompanyId());
        List<UserResourceMark> resourceMarksByUser = userResourceMarkRepository.findResourceMarksByUser(user.getUserId());
        List<Integer> filteredMarks = resourceMarksByUser.stream().map(userResourceMark -> userResourceMark.getResource().getId()).collect(Collectors.toList());

        List<CompanyResource> companyResources = companyResourcesByCompany.stream().filter(companyResource -> !filteredMarks.contains(companyResource.getResource().getId())).collect(Collectors.toList());

        List<ResourceCompanyRecommendation> recommendations = new ArrayList<>();
        for (CompanyResource resource : companyResources) {
            ResourceCompanyRecommendation resourceCompanyRecommendation = new ResourceCompanyRecommendation(resource.getCompany(), resource.getUser().getLogin(), resource.getResource(), resource.getAction(), resource.getMark());
            recommendations.add(resourceCompanyRecommendation);
        }

        return recommendations;
    }
}
