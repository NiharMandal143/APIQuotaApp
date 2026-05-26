package com.practice.api_quota_app.service;



import com.practice.api_quota_app.entity.User;
import com.practice.api_quota_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User login(String username, String password) {

        Optional<User> user = userRepository
                .findByUsernameAndPassword(username, password);

        return user.orElse(null);
    }
}