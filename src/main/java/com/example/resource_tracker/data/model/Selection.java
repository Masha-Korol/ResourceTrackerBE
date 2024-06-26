package com.example.resource_tracker.data.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "selections", schema = "public")
public class Selection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;

    @ManyToMany()
    @JoinTable(
            name = "selection_resources",
            joinColumns = @JoinColumn(name = "selection_id"),
            inverseJoinColumns = @JoinColumn(name = "resource_id")
    )
    private List<Resource> resources;

    public Integer getId() {
        return id;
    }

    public void setId(Integer selectionId) {
        this.id = selectionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Selection selection = (Selection) o;
        return Objects.equals(id, selection.id) && Objects.equals(name, selection.name) && Objects.equals(description, selection.description) && Objects.equals(resources, selection.resources);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, resources);
    }
}
