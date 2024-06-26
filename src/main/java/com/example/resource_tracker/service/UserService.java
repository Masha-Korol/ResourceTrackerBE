package com.example.resource_tracker.service;

import com.example.resource_tracker.data.dto.user.UserDto;
import com.example.resource_tracker.data.mapper.user.UserMapper;
import com.example.resource_tracker.data.model.AuthorizationRole;
import com.example.resource_tracker.data.model.ERoleType;
import com.example.resource_tracker.data.model.User;
import com.example.resource_tracker.repository.AuthorizationRoleRepository;
import com.example.resource_tracker.repository.CompanyRepository;
import com.example.resource_tracker.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ResourceService resourceService;
    private final CompanyRepository companyRepository;
    private final UserMapper userMapper;
    private final AuthorizationRoleRepository authorizationRoleRepository;

    public UserService(UserRepository userRepository, ResourceService resourceService, CompanyRepository companyRepository,
                       UserMapper userMapper,
                       AuthorizationRoleRepository authorizationRoleRepository) {
        this.userRepository = userRepository;
        this.resourceService = resourceService;
        this.companyRepository = companyRepository;
        this.userMapper = userMapper;
        this.authorizationRoleRepository = authorizationRoleRepository;
    }

    public UserDto get(Integer id) {
        return (UserDto) userRepository.findById(id);
    }

    public UserDto getInfo(Integer id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            byId.resources = resourceService.getAllByUser(id);
            return byId;
        }
        return null;
    }

    public List<UserDto> getAll() {
        return (List<UserDto>) userRepository.findAll();
    }

    public void addUser(UserDto dto) {
        User newUser = userMapper.toEntity(dto);

        /*  On db start we suppose to already have ROLE_USER at least
            but if don't - we are going to add it to our authorizationRoleRepository
            so any registered user will have ERoleType.ROLE_USER afterwards */
        Set<AuthorizationRole> newUserRoles = new HashSet<>();
        boolean isRolePresentedInRepository = authorizationRoleRepository.findByRoleName(ERoleType.ROLE_USER).isPresent();
        AuthorizationRole standardRole;
        if (isRolePresentedInRepository) {
            standardRole = authorizationRoleRepository.findByRoleName(ERoleType.ROLE_USER).get();
        } else {
            standardRole = new AuthorizationRole();
            standardRole.setRoleName(ERoleType.ROLE_USER);
            authorizationRoleRepository.save(standardRole);
        }
        newUserRoles.add(standardRole);
        newUser.setRoles(newUserRoles);
        userRepository.save(newUser);
    }

    public UserDto editUser(UserDto dto) {
        User save = userRepository.save(userMapper.toEntity(dto));
        return userMapper.toDto(save);
    }

    public void deleteUser(Integer id) {
        userRepository.delete(userRepository.findById(id).get());
    }

    public void editUserCompany(Optional<Integer> newCompanyId) {
        Optional<User> user = userRepository.findById(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user.isPresent()) {
            if (newCompanyId.isPresent()) {
                user.get().setCompany(companyRepository.findById(newCompanyId.get()).get());
            } else {
                user.get().setCompany(null);
            }
        }
    }

    public void editUserTags(List<Integer> tagIds) {
        Optional<User> user = userRepository.findById(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user.isPresent()) {
            user.get().setTags(tagIds);
        }
    }

    public Optional<UserDto> findByLogin(String username) {
        return userRepository.findByLogin(username);
    }
}
