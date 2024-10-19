package com.adem.spring_board.service;

import com.adem.spring_board.model.User;
import com.adem.spring_board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User getUser(String email) {
        return userRepository.findByUsername(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void validateUser(Long id) {
        userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
}

