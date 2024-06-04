package com.example.concert_service.repository;

import com.example.concert_service.data.model.AuthorizationRole;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface AuthorizationRoleRepository extends PagingAndSortingRepository<AuthorizationRole, Integer> {
    Optional<AuthorizationRole> findByRoleName(ERoleType roleUser);
}
