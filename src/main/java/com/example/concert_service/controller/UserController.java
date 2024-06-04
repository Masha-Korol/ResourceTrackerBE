package com.example.concert_service.controller;

import com.example.concert_service.data.dto.user.UserDto;
import com.example.concert_service.data.model.User;
import com.example.concert_service.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public void add(@RequestBody UserDto user){
        userService.addUser(user);
    }

    @PutMapping("/")
    public UserDto edit(@RequestBody UserDto user){
        return userService.editUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    @GetMapping("/")
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Integer id) {
        return userService.getInfo(id);
    }

    @PutMapping("/company")
    public void editUserCompany(@RequestBody(required = false) Optional<Integer> companyId) {
        userService.editUserCompany(companyId);
    }

    @PutMapping("/tags")
    public void editUserTags(@RequestBody List<Integer> tagIds) {
        userService.editUserTags(tagIds);
    }
}
