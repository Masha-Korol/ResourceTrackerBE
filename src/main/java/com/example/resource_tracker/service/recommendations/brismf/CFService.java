package com.example.resource_tracker.service.recommendations.brismf;

import com.example.resource_tracker.data.model.Resource;
import com.example.resource_tracker.data.model.UserResourceMark;
import com.example.resource_tracker.data.model.User;
import com.example.resource_tracker.repository.UserResourceMarkRepository;
import com.example.resource_tracker.repository.ResourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CFService {

    private final BRISMFService brismfService;
    private final UserResourceMarkRepository userResourceMarkRepository;
    private final ResourceRepository resourceRepository;

    public CFService(BRISMFService brismfService, UserResourceMarkRepository userResourceMarkRepository, ResourceRepository resourceRepository) {
        this.brismfService = brismfService;
        this.userResourceMarkRepository = userResourceMarkRepository;
        this.resourceRepository = resourceRepository;
    }

    public List<Resource> getRecommendations(User currentUser) {
        List<UserResourceMark> userMarks = userResourceMarkRepository.findResourceMarksByUser(currentUser.getId());
        List<Integer> markedResources = userMarks.stream().map(userResourceMark -> userResourceMark.getResource().getId()).collect(Collectors.toList());

        List<Integer> recommendations = brismfService.getTopFiveUserPredictions(currentUser.getId(), markedResources);
        return recommendations.stream().map(resourceId -> resourceRepository.findById(resourceId).get()).collect(Collectors.toList());
    }
}
