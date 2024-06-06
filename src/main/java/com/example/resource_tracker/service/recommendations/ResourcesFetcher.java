package com.example.resource_tracker.service.recommendations;

import com.example.resource_tracker.data.model.Resource;
import com.example.resource_tracker.data.model.ResourceState;
import com.example.resource_tracker.data.model.ResourceType;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.Set;

@Service
public class ResourcesFetcher {

    private static final String DEV_TO_API_URL = "https://dev.to/api/articles";
    private static final HttpClient httpClient = HttpClient.newHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Scheduled(cron = "0 0 0 * * MON") // Every Monday at midnight
    public void fetchResources() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(DEV_TO_API_URL))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                JsonNode articles = objectMapper.readTree(response.body());
                processArticles(articles);
            } else {
                System.err.println("Failed to fetch articles. HTTP status code: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processArticles(JsonNode articles) {
        if (articles.isArray()) {
            for (JsonNode article : articles) {
                Integer id = article.get("id").asInt();
                String title = article.get("title").asText();
                String url = article.get("url").asText();

                Set<String> tags = new HashSet<>();
                JsonNode tagList = article.get("tag_list");
                if (tagList.isArray()) {
                    for (JsonNode tag : tagList) {
                        tags.add(tag.asText());
                    }
                }

                Resource resource = new Resource(title, ResourceType.ARTICLE, url, ResourceState.APPROVED, tags);
            }
        }
    }
}
