package com.example.concert_service.service.recommendations;

import com.example.concert_service.data.model.Resource;
import com.example.concert_service.repository.ResourceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KNNService {

    private final ResourceRepository resourceRepository;

    public KNNService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public List<Resource> getRecommendations(Integer resourceId) {
        List<ResourceWithTagsBinary> normalizedData = this.getNormalizedData(resourceId);
        List<Integer> similarResources = this.getSimilarResources(normalizedData);
        return similarResources.stream()
                .map(id -> resourceRepository.findById(id).get())
                .collect(Collectors.toList());
    }

    private List<ResourceWithTagsBinary> getNormalizedData(Integer resourceId) {
        List<Resource> all = (List<Resource>) resourceRepository.findAll();
        List<Resource> filteredResources = all.stream().filter(resource -> resource.getId().equals(resourceId)).collect(Collectors.toList());

        return new ArrayList<>();
    }

    private List<Integer> getSimilarResources(List<ResourceWithTagsBinary> normalizedData) {
        return new ArrayList<>();
    }

    private double getCosineSimilarity(ResourceWithTagsBinary resource1, ResourceWithTagsBinary resource2) {

    }
}
