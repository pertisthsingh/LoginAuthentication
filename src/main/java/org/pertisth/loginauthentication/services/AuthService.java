package org.pertisth.loginauthentication.services;

import org.bson.types.ObjectId;
import org.pertisth.loginauthentication.models.User;
import org.pertisth.loginauthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
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
