package com.example.resource_tracker.service;

import com.example.resource_tracker.data.model.Company;
import com.example.resource_tracker.repository.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public void add(Company company) {
        companyRepository.save(company);
    }

    public void delete(Integer id) {
        companyRepository.delete(companyRepository.findById(id).get());
    }
}
