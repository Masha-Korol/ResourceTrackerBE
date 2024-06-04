package com.example.concert_service.repository;

import com.example.concert_service.data.model.Resource;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ResourceRepository extends PagingAndSortingRepository<Resource, Integer> {
}
