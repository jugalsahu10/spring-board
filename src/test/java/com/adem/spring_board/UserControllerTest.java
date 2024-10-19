package com.adem.spring_board;

import com.adem.spring_board.dto.AuthRequest;
import com.adem.spring_board.model.User;
import com.adem.spring_board.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired private MockMvc mockMvc;

    @Autowired private UserRepository userRepository;  // You can also mock this if needed

    @Autowired private ObjectMapper objectMapper;  // To convert Java objects to JSON

    @Test
    void testRegisterUser() throws Exception {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/register").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(user))).andExpect(status().isOk());
    }

    @Test
    void testLoginUser() throws Exception {
        // Assume user is already registered

        AuthRequest authRequest = new AuthRequest();
        authRequest.setUsername("testuser");
        authRequest.setPassword("password");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/login").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(authRequest))).andExpect(status().isOk());
    }
}

