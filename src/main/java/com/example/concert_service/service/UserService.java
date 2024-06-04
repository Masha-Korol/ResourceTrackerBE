package com.example.concert_service.service;

import com.example.concert_service.data.dto.user.UserDto;
import com.example.concert_service.data.mapper.user.UserMapper;
import com.example.concert_service.data.model.AuthorizationRole;
import com.example.concert_service.data.model.ERoleType;
import com.example.concert_service.data.model.User;
import com.example.concert_service.repository.AuthorizationRoleRepository;
import com.example.concert_service.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthorizationRoleRepository authorizationRoleRepository;

    public UserService(UserRepository userRepository,
                       UserMapper userMapper,
                       AuthorizationRoleRepository authorizationRoleRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.authorizationRoleRepository = authorizationRoleRepository;
    }

    public List<User> getAll(){
        return (List<User>) userRepository.findAll();
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
}
