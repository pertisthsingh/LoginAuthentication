package org.pertisth.loginauthentication.services.impl;

import org.pertisth.loginauthentication.models.User;
import org.pertisth.loginauthentication.models.UserPrinciple;
import org.pertisth.loginauthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    public UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUsersByUsername(username);
        return new UserPrinciple(user);
    }
}
