package org.pertisth.loginauthentication.controllers;


import org.pertisth.loginauthentication.models.User;
import org.pertisth.loginauthentication.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class Users {
    public UserRepository userRepository;
    public Users(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/allusers")
    public List<User> allUsers() {
        return userRepository.findAll();
    }
}
