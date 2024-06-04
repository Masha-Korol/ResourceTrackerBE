package com.example.concert_service.data.model;

import javax.persistence.*;

@Entity
@Table(name = "resource_actions", schema = "public")
public class ResourceAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer actionId;
    private String name;
    private Integer mark;

    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }
}
