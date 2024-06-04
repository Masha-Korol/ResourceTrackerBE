package com.example.resource_tracker.repository;

import com.example.resource_tracker.data.model.Project;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProjectRepository extends PagingAndSortingRepository<Project, Integer> {
}
