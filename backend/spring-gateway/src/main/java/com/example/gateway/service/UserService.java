package com.example.gateway.service;

import com.example.gateway.domain.User;
import com.example.gateway.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    final private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(String email, String userName) {
        if (userRepository.findByEmail(email).isPresent()) {
            return;
        }
        User user = User.builder().userName(userName).email(email).build();
        userRepository.save(user);
    }
}
