package com.example.concert_service.service.recommendations;

import com.example.concert_service.data.model.Resource;
import com.example.concert_service.data.model.UserResourceMark;
import com.example.concert_service.data.model.User;
import com.example.concert_service.repository.UserResourceMarkRepository;
import com.example.concert_service.repository.ResourceRepository;
import com.example.concert_service.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CFService {

    private final BRISMFService brismfService;
    private final UserService userService;
    private final UserResourceMarkRepository userResourceMarkRepository;
    private final ResourceRepository resourceRepository;

    public CFService(BRISMFService brismfService, UserService userService, UserResourceMarkRepository userResourceMarkRepository, ResourceRepository resourceRepository) {
        this.brismfService = brismfService;
        this.userService = userService;
        this.userResourceMarkRepository = userResourceMarkRepository;
        this.resourceRepository = resourceRepository;
    }

    public List<Resource> getRecommendations() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByLogin(username).get();
        List<UserResourceMark> userMarks = userResourceMarkRepository.findResourceMarksByUser(user.getUserId());
        List<Integer> markedResources = userMarks.stream().map(userResourceMark -> userResourceMark.getResource().getId()).collect(Collectors.toList());

        List<Integer> recommendations = brismfService.getTopFiveUserPredictions(user.getUserId(), markedResources);
        return recommendations.stream().map(resourceId -> resourceRepository.findById(resourceId).get()).collect(Collectors.toList());
    }
}
