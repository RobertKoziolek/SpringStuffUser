package com.robcio.springstuff.service;

import com.robcio.springstuff.entity.User;
import com.robcio.springstuff.exception.UserNotFoundException;
import com.robcio.springstuff.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void getUser() {
        //given
        final long userId = 9L;
        final User user = new User();
        user.setId(userId);

        //when
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        final User userFound = userService.getUser(userId);

        //then
        assertThat(userFound).isNotNull();
        assertThat(userFound.getId()).isEqualTo(userId);
    }

    @Test(expected = UserNotFoundException.class)
    public void getUserNotFound() {
        //given
        final long userId = 9L;

        //when
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        final User userFound = userService.getUser(userId);

        //then
    }
}