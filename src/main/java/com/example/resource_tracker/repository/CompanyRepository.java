package com.example.resource_tracker.repository;

import com.example.resource_tracker.data.model.Company;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CompanyRepository extends PagingAndSortingRepository<Company, Integer> {
}
