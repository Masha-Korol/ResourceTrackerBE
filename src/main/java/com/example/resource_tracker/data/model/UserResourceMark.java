package com.example.resource_tracker.data.model;

import javax.persistence.*;

@Entity
@Table(name = "user_resource_marks", schema = "public")
public class UserResourceMark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer mark;
    private Boolean isFavorite;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "resource_id")
    private Resource resource;

    public UserResourceMark(User user, Resource resource, Integer mark, Boolean isFavorite) {
        this.user = user;
        this.resource = resource;
        this.mark = mark;
        this.isFavorite = isFavorite;
    }

    public UserResourceMark() {}

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

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }
}
