package com.example.resource_tracker.service.recommendations.knn;

public class ResourceSimilarity {
    private ResourceWithTagsBinary resource;
    private double similarity;

    public ResourceSimilarity(ResourceWithTagsBinary resource, double similarity) {
        this.resource = resource;
        this.similarity = similarity;
    }

    public ResourceWithTagsBinary getResource() {
        return resource;
    }

    public double getSimilarity() {
        return similarity;
    }
}
