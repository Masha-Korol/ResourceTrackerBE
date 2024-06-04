package com.example.concert_service.controller;

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
    public void add(@RequestBody User user){
        userService.add(user);
    }

    @GetMapping("/")
    public List<User> getAll(){
        return userService.getAll();
    }
}
