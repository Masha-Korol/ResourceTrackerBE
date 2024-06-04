package com.example.resource_tracker.repository;

import com.example.resource_tracker.data.model.CompanyResource;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CompanyResourceRepository extends PagingAndSortingRepository<CompanyResource, Integer> {
    public List<CompanyResource> findCompanyResourcesByCompany(Integer companyId);
}
