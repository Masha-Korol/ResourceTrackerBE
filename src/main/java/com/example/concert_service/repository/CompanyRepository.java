package com.example.concert_service.repository;

import com.example.concert_service.data.model.Company;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CompanyRepository extends PagingAndSortingRepository<Company, Integer> {
}
