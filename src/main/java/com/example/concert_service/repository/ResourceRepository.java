package com.example.concert_service.repository;

import com.example.concert_service.data.model.Resource;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ResourceRepository extends PagingAndSortingRepository<Resource, Integer> {

}
