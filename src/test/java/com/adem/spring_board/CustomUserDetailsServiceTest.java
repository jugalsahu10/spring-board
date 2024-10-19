package com.adem.spring_board;

import com.adem.spring_board.model.User;
import com.adem.spring_board.repository.UserRepository;
import com.adem.spring_board.service.CustomUserDetailsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class CustomUserDetailsServiceTest {

    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private final CustomUserDetailsService userDetailsService = new CustomUserDetailsService(userRepository);

    @Test
    void loadUserByUsername_UserExists() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));

        var userDetails = userDetailsService.loadUserByUsername("testuser");

        assertEquals("testuser", userDetails.getUsername());
    }

    @Test
    void loadUserByUsername_UserNotFound() {
        when(userRepository.findByUsername("unknownuser")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername("unknownuser"));
    }
}

