package com.example.concert_service.repository;

import com.example.concert_service.data.model.Tag;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TagRepository extends PagingAndSortingRepository<Tag, Integer> {
}
