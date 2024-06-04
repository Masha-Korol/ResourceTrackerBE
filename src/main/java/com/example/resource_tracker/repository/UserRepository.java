package com.example.resource_tracker.repository;

import com.example.resource_tracker.data.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
    Optional<User> findByLogin(String username);
    Boolean existsByLogin(String username);
}
