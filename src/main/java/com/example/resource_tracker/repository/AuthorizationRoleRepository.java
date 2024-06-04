package com.example.resource_tracker.repository;

import com.example.resource_tracker.data.model.AuthorizationRole;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface AuthorizationRoleRepository extends PagingAndSortingRepository<AuthorizationRole, Integer> {
    Optional<AuthorizationRole> findByRoleName(ERoleType roleUser);
}
