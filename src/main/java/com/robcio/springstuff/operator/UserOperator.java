package com.robcio.springstuff.operator;

import com.robcio.springstuff.entity.User;
import com.robcio.springstuff.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserOperator {

    private final UserRepository userRepository;

    @Autowired
    public UserOperator(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getRandomUser() {
        return userRepository.findRandom();
    }
}
