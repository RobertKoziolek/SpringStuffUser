package com.robcio.springstuff.controller.user;

import com.robcio.springstuff.controller.UserRestController;
import com.robcio.springstuff.entity.User;
import com.robcio.springstuff.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@UserRestController
public class GetUserController {

    private static final Logger logger = LoggerFactory.getLogger(GetUserController.class);

    private final UserService userService;

    @Autowired
    public GetUserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{userId}")
    @ApiOperation(value = "Returns a user by id")
    public User getAllUsers(@PathVariable final Long userId) {
        logger.debug("Returning user with id {}", userId);
        return userService.getUser(userId);
    }
}
