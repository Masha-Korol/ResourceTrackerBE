package com.example.concert_service.data.dto.resource;

import com.example.concert_service.data.model.Company;
import com.example.concert_service.data.model.Resource;
import com.example.concert_service.data.model.UserAction;

public class ResourceCompanyRecommendation {

    private Integer id;
    private Company company;
    private String userName;
    private Resource resource;
    private UserAction action;
    private Integer mark;

    public ResourceCompanyRecommendation(Company company, String userName, Resource resource, UserAction action, Integer mark) {
        this.company = company;
        this.userName = userName;
        this.resource = resource;
        this.action = action;
        this.mark = mark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public UserAction getAction() {
        return action;
    }

    public void setAction(UserAction action) {
        this.action = action;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }
}
