package com.example.resource_tracker.service;

import com.example.resource_tracker.data.dto.resource.ResourceDto;
import com.example.resource_tracker.data.mapper.resource.ResourceCreationMapper;
import com.example.resource_tracker.data.model.*;
import com.example.resource_tracker.repository.UserResourceMarkRepository;
import com.example.resource_tracker.repository.ResourceRepository;
import com.example.resource_tracker.repository.TagRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ResourceService {

    private final ResourceRepository resourceRepository;
    private final UserResourceMarkRepository userResourceMarkRepository;
    private final ResourceCreationMapper resourceCreationMapper;
    private final TagRepository tagRepository;
    private final UserService userService;

    public ResourceService(ResourceRepository resourceRepository, UserResourceMarkRepository userResourceMarkRepository,
                           ResourceCreationMapper resourceCreationMapper, TagRepository tagRepository, UserService userService) {
        this.resourceRepository = resourceRepository;
        this.userResourceMarkRepository = userResourceMarkRepository;
        this.resourceCreationMapper = resourceCreationMapper;
        this.tagRepository = tagRepository;
        this.userService = userService;
    }

    public List<UserResourceMark> getAllByUser(Integer id) {
        return userResourceMarkRepository.findResourceMarksByUser(id);
    }

    public List<ResourceDto> getAllByUserTags(Integer id) {
        return new ArrayList<>();
    }

    public ResourceDto getById(Integer id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByLogin(username).get();

        ResourceDto dto = resourceCreationMapper.toDto(resourceRepository.findById(id).orElse(null));
        List<UserResourceMark> userMark = userResourceMarkRepository.findResourceMarksByUser(user.getUserId());
        dto.setMark(userMark.get(0).getMark());
        dto.setFavorite(userMark.get(0).getFavorite());
        return dto;
    }

    public void add(ResourceDto resource){
        resourceRepository.save(resourceCreationMapper.toEntity(resource));
    }

    public ResourceDto edit(ResourceDto resource) {
        Resource save = resourceRepository.save(resourceCreationMapper.toEntity(resource));
        return resourceCreationMapper.toDto(save);
    }

    public void delete(Integer id) {
        Optional<Resource> byId = resourceRepository.findById(id);
        byId.ifPresent(resourceRepository::delete);
    }

    public List<ResourceDto> getAll(List<Long> tagIds, String name, String type, String state) {
        return resourceCreationMapper.toDto(resourceRepository.findAll());
    }

    public void markResource(Integer id, Integer mark) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByLogin(username).get();

        UserResourceMark markEntity = userResourceMarkRepository.findResourceMarkByUserAndResource(user.getUserId(), id);
        if (markEntity != null) {
            markEntity.setMark(mark);
            userResourceMarkRepository.save(markEntity);
        } else {
            Optional<Resource> resource = resourceRepository.findById(id);
            userResourceMarkRepository.save(new UserResourceMark(user, resource.get(), mark, false));
        }
    }

    public void addOrRemoveResourceFromFavorites(Integer id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByLogin(username).get();

        UserResourceMark mark = userResourceMarkRepository.findResourceMarkByUserAndResource(user.getUserId(), id);
        mark.setFavorite(!mark.getFavorite());
    }

    public void changeResourceState(Integer id, ResourceState state) {
        Optional<Resource> resource = resourceRepository.findById(id);
        if (resource.isPresent()) {
            Resource updatedResource = resource.get();
            updatedResource.setState(state);
            resourceRepository.save(updatedResource);
        }
    }

    public void addTag(Tag tag) {
        tagRepository.save(tag);
    }
}
