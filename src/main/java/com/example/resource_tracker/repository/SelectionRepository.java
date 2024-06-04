package com.example.resource_tracker.repository;

import com.example.resource_tracker.data.model.Selection;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SelectionRepository extends PagingAndSortingRepository<Selection, Integer> {
}
