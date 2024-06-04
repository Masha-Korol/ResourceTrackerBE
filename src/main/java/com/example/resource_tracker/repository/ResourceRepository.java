package com.example.resource_tracker.repository;

import com.example.resource_tracker.data.model.Resource;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ResourceRepository extends PagingAndSortingRepository<Resource, Integer> {

}
