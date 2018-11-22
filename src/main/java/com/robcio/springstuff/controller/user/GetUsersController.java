package com.robcio.springstuff.controller.user;

import com.robcio.springstuff.controller.UserRestController;
import com.robcio.springstuff.entity.User;
import com.robcio.springstuff.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@UserRestController
public class GetUsersController {

    private static final Logger logger = LoggerFactory.getLogger(GetUsersController.class);

    private final UserService userService;

    @Autowired
    public GetUsersController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/")
    @ApiOperation(value = "Returns all users")
    public List<User> getAllUsers() {
        logger.debug("Returning all users");
        return userService.getAll();
    }
}
