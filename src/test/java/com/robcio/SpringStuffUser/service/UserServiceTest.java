package com.robcio.SpringStuffUser.service;

import com.robcio.SpringStuffUser.entity.User;
import com.robcio.SpringStuffUser.exception.UserNotFoundException;
import com.robcio.SpringStuffUser.repository.UserRepository;
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