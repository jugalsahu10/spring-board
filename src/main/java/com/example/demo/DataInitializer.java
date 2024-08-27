package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired private UserRepository userRepository;

    @Override
    public void run(String... args) {
        // Check if the database is already populated
        if (userRepository.count() > 0) {
            return;
        }

        // Insert 15 users
        IntStream.rangeClosed(1, 15).forEach(i -> {
            User user = new User(null, "user" + i, "user" + i + "@example.com");
            userRepository.save(user);
        });
    }
}
