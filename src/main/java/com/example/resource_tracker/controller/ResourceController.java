package com.example.resource_tracker.controller;

import com.example.resource_tracker.data.dto.resource.ResourceDto;
import com.example.resource_tracker.data.model.*;
import com.example.resource_tracker.service.CommentService;
import com.example.resource_tracker.service.ResourceService;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping(path = "/api/v1/resources")
public class ResourceController {

    private final ResourceService resourceService;
    private final CommentService commentService;

    public ResourceController(ResourceService resourceService, CommentService commentService) {
        this.resourceService = resourceService;
        this.commentService = commentService;
    }

    @GetMapping("/user/{id}")
    public List<UserResourceMark> getAllForUser(@PathVariable Integer id){
        return resourceService.getAllByUser(id);
    }

    @GetMapping("/by-user-tags/{id}")
    public List<ResourceDto> getAllByUserTags(@PathVariable Integer id){
        return resourceService.getAllByUserTags(id);
    }

    @GetMapping("/")
    public List<ResourceDto> getAll(@RequestParam(required = false) List<Long> tagIds,
                                    @RequestParam(required = false) String name,
                                    @RequestParam(required = false) String type,
                                    @RequestParam(required = false) String state){
        return resourceService.getAll(tagIds, name, type, state);
    }

    @GetMapping("/{id}")
    public ResourceDto getById(@PathVariable Integer id){
        return resourceService.getById(id);
    }

    @PostMapping("/")
    public void addComment(@RequestBody CommentDto comment){
        commentService.addComment(comment);
    }

    @PostMapping("/")
    public ResourceDto add(@RequestBody ResourceDto resource){
        resourceService.add(resource);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        resourceService.delete(id);
    }

    @PutMapping("/")
    public ResourceDto editResource(@RequestBody ResourceDto resourceDto) {
        return resourceService.edit(resourceDto);
    }

    @GetMapping("/mark/{id}")
    public void markResource(@PathVariable Integer id, Integer mark) {
        resourceService.markResource(id, mark);
    }

    @GetMapping("/fav/{id}")
    public void addOrRemoveResourceFromFavorites(@PathVariable Integer id) {
        resourceService.addOrRemoveResourceFromFavorites(id);
    }

    @PutMapping("/state/{id}")
    public void changeResourceState(@PathVariable Integer id, @RequestBody ResourceState state) {
        resourceService.changeResourceState(id, state);
    }

    @PostMapping("/tag")
    public void addTag(@RequestBody Tag tag) {
        resourceService.addTag(tag);
    }
}
