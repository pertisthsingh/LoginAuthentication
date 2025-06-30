package org.pertisth.loginauthentication.services;

import org.pertisth.loginauthentication.models.User;
import org.pertisth.loginauthentication.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        User savedUser = this.getUserByUserName(user.getUsername());
        if(savedUser == null){
            savedUser = userRepository.save(user);
        }
        return savedUser;
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public User getUserByUserName(String username) {
        try {
            return this.userRepository.getUsersByUsername(username);
        }catch (Exception e){
            return null;
        }
    }
}
