package com.example.concert_service.service.recommendations;

import java.util.List;

public class ResourceWithTagsBinary {
    private Integer resourceId;
    private List<Boolean> tagsBinary;

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public List<Boolean> getTagsBinary() {
        return tagsBinary;
    }

    public void setTagsBinary(List<Boolean> tagsBinary) {
        this.tagsBinary = tagsBinary;
    }
}
