package com.example.resource_tracker.service.recommendations.brismf;

import java.util.Objects;

public class EvaluationEntity {

    private Integer userId;
    private Integer resourceId;
    /**
     * Оценка, которой пользователь с индексом userIndex оценил ресурс с индексом itemIndex
     * (null, если оценки нет)
     */
    private Double mark;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    public EvaluationEntity(Integer userIndex, Integer itemIndex, Double mark) {
        this.userId = userIndex;
        this.resourceId = itemIndex;
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EvaluationEntity that = (EvaluationEntity) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(resourceId, that.resourceId) &&
                Objects.equals(mark, that.mark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, resourceId, mark);
    }

    @Override
    public String toString() {
        return "EvaluationEntity{" +
                "userIndex=" + userId +
                ", itemIndex=" + resourceId +
                ", mark=" + mark +
                '}';
    }
}
