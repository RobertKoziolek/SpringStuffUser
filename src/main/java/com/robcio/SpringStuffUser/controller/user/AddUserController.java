package com.robcio.SpringStuffUser.controller.user;

import com.robcio.SpringStuffUser.controller.UserRestController;
import com.robcio.SpringStuffUser.controller.request.UserRequest;
import com.robcio.SpringStuffUser.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@UserRestController
public class AddUserController {

    private static final Logger logger = LoggerFactory.getLogger(AddUserController.class);

    private final UserService userService;

    @Autowired
    public AddUserController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/add")
    @ApiOperation(value = "Adds a user")
    public Long add(@RequestBody final UserRequest user) {
        logger.debug("Adding user {}", user.getName());
        return userService.add(user);
    }
}
