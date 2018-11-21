package com.robcio.springstuff.service;

import com.robcio.springstuff.entity.User;
import com.robcio.springstuff.exception.UserNotFoundException;
import com.robcio.springstuff.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getRandomUser() {
        return userRepository.findRandom();
    }

    @NotNull
    public User getUser(final Long userId) {
        return userRepository.findById(userId)
                             .orElseThrow(UserNotFoundException::new);
    }

}
