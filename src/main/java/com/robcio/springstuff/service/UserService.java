package com.robcio.springstuff.service;

import com.robcio.springstuff.controller.request.UserRequest;
import com.robcio.springstuff.entity.User;
import com.robcio.springstuff.exception.UserNotFoundException;
import com.robcio.springstuff.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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

    public Long add(final UserRequest userRequest) {
        final User user = new User();
        validateUserRequest(userRequest);
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setAge(userRequest.getAge());
        userRepository.save(user);
        return user.getId();
    }

    private void validateUserRequest(final UserRequest userRequest) {
        assertThat(userRequest.getAge()).isNotNull();
        assertThat(userRequest.getName()).isNotNull();
        assertThat(userRequest.getEmail()).isNotNull();
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

}
