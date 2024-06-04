package com.example.concert_service.controller;

import com.example.concert_service.data.model.Company;
import com.example.concert_service.service.CompanyService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/")
    public void add(@RequestBody Company company) {
        companyService.add(company);
    }

    @PutMapping("/")
    public void edit(@RequestBody Company company) {
        companyService.add(company);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        companyService.delete(id);
    }
}
