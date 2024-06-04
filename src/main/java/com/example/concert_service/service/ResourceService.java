package com.example.concert_service.service;

import com.example.concert_service.data.dto.resource.ResourceDto;
import com.example.concert_service.data.mapper.resource.ResourceCreationMapper;
import com.example.concert_service.data.model.Resource;
import com.example.concert_service.data.model.ResourceMark;
import com.example.concert_service.data.model.Statistics;
import com.example.concert_service.repository.ResourceMarkRepository;
import com.example.concert_service.repository.ResourceRepository;
import com.example.concert_service.repository.StatisticsRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ResourceService {

    private final ResourceRepository resourceRepository;
    private final ResourceMarkRepository resourceMarkRepository;
    private final StatisticsRepository statisticsRepository;
    private final ResourceCreationMapper resourceCreationMapper;

    public ResourceService(ResourceRepository resourceRepository, ResourceMarkRepository resourceMarkRepository, StatisticsRepository statisticsRepository,
                           ResourceCreationMapper resourceCreationMapper) {
        this.resourceRepository = resourceRepository;
        this.resourceMarkRepository = resourceMarkRepository;
        this.statisticsRepository = statisticsRepository;
        this.resourceCreationMapper = resourceCreationMapper;
    }

    public void add(ResourceDto artist){
        resourceRepository.save(resourceCreationMapper.toEntity(artist));
    }

    public void delete(Integer id) {
        resourceRepository.delete(id);
    }

    public List<ResourceDto> getAll(){
        return resourceCreationMapper.toDto((List<Resource>) resourceRepository.findAll());
    }

    public void markResource(Integer id, Integer mark) {
        resourceMarkRepository.save(new ResourceMark(id, mark));
    }

    public List<Statistics> getStatistics() {
        return statisticsRepository.findAll();
    }
}
