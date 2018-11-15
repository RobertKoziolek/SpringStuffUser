package com.robcio.springstuff.controller;

import com.robcio.springstuff.entity.User;
import com.robcio.springstuff.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/users")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(path = "/add")
    public String add(@ModelAttribute final User user) {
        userRepository.save(user);
        return "redirect:/users/";
    }

    @GetMapping(path = "/")
    public String getAllUsers(final Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users";
    }
}
