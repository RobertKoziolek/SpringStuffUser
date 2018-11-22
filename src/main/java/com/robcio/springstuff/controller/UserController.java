package com.robcio.springstuff.controller;

import com.robcio.springstuff.controller.request.UserRequest;
import com.robcio.springstuff.entity.User;
import com.robcio.springstuff.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
@Api(value = "/users", description = "Operations about users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/add")
    @ApiOperation(value = "Adds a user")
    public Long add(@RequestBody final UserRequest user) {
        logger.debug("Adding user {}", user.getName());
        return userService.add(user);
    }

    @GetMapping(path = "/")
    @ApiOperation(value = "Returns all users")
    public List<User> getAllUsers() {
        logger.debug("Returning all users");
        return userService.getAll();
    }

    @GetMapping(path = "/{userId}")
    @ApiOperation(value = "Returns a user by id")
    public User getAllUsers(@PathVariable final Long userId) {
        logger.debug("Returning user with id {}", userId);
        return userService.getUser(userId);
    }
}
