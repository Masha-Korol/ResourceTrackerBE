package com.example.concert_service.data.dto.resource;

import com.example.concert_service.data.model.ResourceType;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ResourceDto {

    private String name;
    private ResourceType type;
    private String link;
    private Set<String> tags = new HashSet<>();
    private Integer mark;
    private Boolean isFavorite;

    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResourceDto that = (ResourceDto) o;
        return Objects.equals(name, that.name) && type == that.type && Objects.equals(link, that.link) && Objects.equals(tags, that.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, link, tags);
    }

    @Override
    public String toString() {
        return "ResourceDto{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", link='" + link + '\'' +
                ", tags=" + tags +
                '}';
    }
}
