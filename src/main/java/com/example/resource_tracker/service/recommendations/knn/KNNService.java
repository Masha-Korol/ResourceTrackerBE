package com.example.resource_tracker.service.recommendations.knn;

import com.example.resource_tracker.data.model.Resource;
import com.example.resource_tracker.data.model.Tag;
import com.example.resource_tracker.repository.ResourceRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class KNNService {

    private final ResourceRepository resourceRepository;

    public KNNService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public List<Resource> getRecommendations(Integer resourceId) {
        List<ResourceWithTagsBinary> normalizedData = this.getNormalizedData();
        ResourceWithTagsBinary targetResourceNormalized = normalizedData.stream().filter(resourceWithTagsBinary -> resourceWithTagsBinary.getResourceId().equals(resourceId)).collect(Collectors.toList()).get(0);
        normalizedData.remove(targetResourceNormalized);

        List<Integer> similarResources = this.getSimilarResources(targetResourceNormalized, normalizedData);
        return similarResources.stream()
                .map(id -> resourceRepository.findById(id).get())
                .collect(Collectors.toList());
    }

    private List<ResourceWithTagsBinary> getNormalizedData() {
        List<Resource> resources = (List<Resource>) resourceRepository.findAll();

        List<Tag> allTagsList = resources.stream().flatMap(resource -> resource.getTags().stream()).distinct().collect(Collectors.toList());

        Map<Tag, Integer> tagIndexMap = new HashMap<>();
        for (int i = 0; i < allTagsList.size(); i++) {
            tagIndexMap.put(allTagsList.get(i), i);
        }

        List<ResourceWithTagsBinary> binaryResources = new ArrayList<>();
        for (Resource resource : resources) {
            List<Boolean> tagsBinary = new ArrayList<>(Collections.nCopies(allTagsList.size(), false));

            for (Tag tag : resource.getTags()) {
                int index = tagIndexMap.get(tag);
                tagsBinary.set(index, true);
            }

            binaryResources.add(new ResourceWithTagsBinary(resource.getId(), tagsBinary));
        }

        return binaryResources;
    }

    private List<Integer> getSimilarResources(ResourceWithTagsBinary targetResource, List<ResourceWithTagsBinary> normalizedData) {
        PriorityQueue<ResourceSimilarity> queue = new PriorityQueue<>(5, Comparator.comparingDouble(ResourceSimilarity::getSimilarity).reversed());

        for (ResourceWithTagsBinary resource : normalizedData) {
            double similarity = getCosineSimilarity(targetResource, resource);
            if (queue.size() < 5) {
                queue.offer(new ResourceSimilarity(resource, similarity));
            } else if (similarity > queue.peek().getSimilarity()) {
                queue.poll();
                queue.offer(new ResourceSimilarity(resource, similarity));
            }
        }

        List<Integer> topResources = new ArrayList<>();
        for (int i = 0; i < 5 && !queue.isEmpty(); i++) {
            topResources.add(queue.poll().getResource().getResourceId());
        }

        return topResources;
    }

    private double getCosineSimilarity(ResourceWithTagsBinary resource1, ResourceWithTagsBinary resource2) {
        List<Boolean> vectorA = resource1.getTagsBinary();
        List<Boolean> vectorB = resource2.getTagsBinary();

        int dotProduct = 0;
        int normA = 0;
        int normB = 0;

        for (int i = 0; i < vectorA.size(); i++) {
            boolean a = vectorA.get(i);
            boolean b = vectorB.get(i);

            dotProduct += (a ? 1 : 0) * (b ? 1 : 0);
            normA += (a ? 1 : 0);
            normB += (b ? 1 : 0);
        }

        if (normA == 0 || normB == 0) {
            return 0.0;
        }

        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }
}
