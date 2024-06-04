package com.example.resource_tracker.data.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tags", schema = "public")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tagName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer tagId) {
        this.id = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(id, tag.id) && Objects.equals(tagName, tag.tagName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tagName);
    }
}
