package com.example.resource_tracker.data.dto;

import com.example.resource_tracker.data.model.UserAction;

public class StatActionDto {

    private Integer resourceId;
    private UserAction action;
    private Integer mark;

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
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
