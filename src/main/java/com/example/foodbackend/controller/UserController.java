package com.example.foodbackend.controller;

import com.example.foodbackend.entity.UserEntity;
import com.example.foodbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PreAuthorize("hasRole('Admin')")
    @GetMapping({"/forAdmin"})
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @PreAuthorize("hasRole('User')")
    @GetMapping({"/forUser"})
    public String forUser(){
        return "This URL is only accessible to the user";
    }
    @PostConstruct
    public void initRolesAndUsers(){
        userService.initRoleAndUser();
    }
    @PostMapping({"/registerNewUser"})
    public UserEntity registerNewUser(@RequestBody UserEntity userEntity) {
        return userService.registerNewUser(userEntity);
    }
}
