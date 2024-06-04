package com.example.concert_service.service;

import com.example.concert_service.data.model.Company;
import com.example.concert_service.repository.CompanyRepository;
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
