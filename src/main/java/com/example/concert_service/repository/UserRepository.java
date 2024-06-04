package com.example.concert_service.repository;

import com.example.concert_service.data.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
    Optional<User> findByLogin(String username);
    Boolean existsByLogin(String username);
    Boolean existsByEmail(String email);
}
