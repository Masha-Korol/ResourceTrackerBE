package com.example.concert_service.repository;

import com.example.concert_service.data.model.CompanyResource;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CompanyResourceRepository extends PagingAndSortingRepository<CompanyResource, Integer> {
    public List<CompanyResource> findCompanyResourcesByCompany(Integer companyId);
}
