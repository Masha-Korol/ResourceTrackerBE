package com.example.concert_service.repository;

import com.example.concert_service.data.model.UserResourceMark;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserResourceMarkRepository extends PagingAndSortingRepository<UserResourceMark, Integer> {
    List<UserResourceMark> findResourceMarksByUser(Integer userId);
    UserResourceMark findResourceMarkByUserAndResource(Integer userId, Integer resourceId);
}
