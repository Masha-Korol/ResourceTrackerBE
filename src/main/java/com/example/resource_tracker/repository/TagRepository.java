package com.example.resource_tracker.repository;

import com.example.resource_tracker.data.model.Tag;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TagRepository extends PagingAndSortingRepository<Tag, Integer> {
}
