package com.example.resource_tracker.data.model;

import javax.persistence.*;

@Entity
@Table(name = "companies", schema = "public")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String companyName;
    private String companyDescription;

    public Integer getId() {
        return id;
    }

    public void setId(Integer companyId) {
        this.id = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }
}
