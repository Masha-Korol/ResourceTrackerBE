package com.example.resource_tracker.data.model;

import javax.persistence.*;

@Entity
@Table(name = "statistics", schema = "public")
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Long timestamp;
    private UserAction action;
    private Integer mark;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "compabyId")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "resource_id")
    private Resource resource;

    public Statistics(Long timestamp, UserAction action, User user, Resource resource) {
        this.timestamp = timestamp;
        this.action = action;
        this.user = user;
        this.resource = resource;
    }

    public Statistics() {}

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
