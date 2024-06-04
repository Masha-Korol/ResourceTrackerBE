package com.example.resource_tracker.repository;

import com.example.resource_tracker.data.model.UserResourceMark;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserResourceMarkRepository extends PagingAndSortingRepository<UserResourceMark, Integer> {
    List<UserResourceMark> findResourceMarksByUser(Integer userId);
    UserResourceMark findResourceMarkByUserAndResource(Integer userId, Integer resourceId);
}
