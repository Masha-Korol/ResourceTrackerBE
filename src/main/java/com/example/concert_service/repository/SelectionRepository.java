package com.example.concert_service.repository;

import com.example.concert_service.data.model.Selection;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SelectionRepository extends PagingAndSortingRepository<Selection, Integer> {
}
