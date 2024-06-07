package com.example.resource_tracker.service;

import com.example.resource_tracker.data.model.Resource;
import com.example.resource_tracker.data.model.ResourceState;
import com.example.resource_tracker.data.model.ResourceType;
import com.example.resource_tracker.data.model.Tag;
import com.example.resource_tracker.repository.TagRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ResourcesFetcher {

//    private final TagRepository tagRepository;
//    private static final String DEV_TO_API_URL = "https://dev.to/api/articles";
//    private static final HttpClient httpClient = HttpClient.newHttpClient();
//    private static final ObjectMapper objectMapper = new ObjectMapper();
//
//    public ResourcesFetcher(TagRepository tagRepository) {
//        this.tagRepository = tagRepository;
//    }
//
//    @Scheduled(cron = "0 0 0 * * MON") // Every Monday at midnight
//    public void fetchResources() {
//        try {
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(new URI(DEV_TO_API_URL))
//                    .GET()
//                    .build();
//
//            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//            if (response.statusCode() == 200) {
//                JsonNode articles = objectMapper.readTree(response.body());
//                processArticles(articles);
//            } else {
//                System.err.println("Failed to fetch articles. HTTP status code: " + response.statusCode());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void processArticles(JsonNode articles) {
//        if (articles.isArray()) {
//            for (JsonNode article : articles) {
//                Integer id = article.get("id").asInt();
//                String title = article.get("title").asText();
//                String url = article.get("url").asText();
//
//                Set<Tag> tags = new HashSet<>();
//                List<Tag> allTags = ((ArrayList<Tag>) tagRepository.findAll());
//
//                StringSimilarityService stringSimilarityService = new StringSimilarityServiceImpl(new JaroWinklerStrategy());
//
//                JsonNode tagList = article.get("tag_list");
//                if (tagList.isArray()) {
//                    for (JsonNode tagNode : tagList) {
//                        String tag = tagNode.asText();
//                        Tag theMostSimilarTag = allTags.get(0);
//                        double theBiggestSimilarity = Double.MIN_VALUE;
//                        for (Tag t : allTags) {
//                            double similarity = stringSimilarityService.score(t, tag);
//                            if (similarity > theBiggestSimilarity) {
//                                theBiggestSimilarity = similarity;
//                                theMostSimilarTag = t;
//                            }
//                        }
//
//                        tags.add(theMostSimilarTag);
//                    }
//                }
//
//                Resource resource = new Resource(title, ResourceType.ARTICLE, url, ResourceState.APPROVED, tags);
//            }
//        }
//    }
}
