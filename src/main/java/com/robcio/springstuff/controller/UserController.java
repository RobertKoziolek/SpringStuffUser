package com.robcio.springstuff.controller;

import com.robcio.springstuff.entity.User;
import com.robcio.springstuff.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
@Api(value = "/users", description = "Operations about users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/add")
    @ApiOperation(value = "Adds a user")
    public String add(@ModelAttribute final User user) {
        userService.add(user);
        return "success";
    }

    @GetMapping(path = "/")
    @ApiOperation(value = "Returns all users")
    public List<User> getAllUsers() {
        return userService.findAll();
    }
}
