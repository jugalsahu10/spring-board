package com.adem.spring_board;

import com.adem.spring_board.dto.AuthRequest;
import com.adem.spring_board.model.User;
import com.adem.spring_board.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerTest {

    @Autowired private MockMvc mockMvc;

    @Autowired private UserRepository userRepository;  // You can also mock this if needed

    @Autowired private ObjectMapper objectMapper;  // To convert Java objects to JSON

    @Autowired private JdbcTemplate jdbcTemplate;  // Autowire JdbcTemplate

    @Autowired private PasswordEncoder passwordEncoder;


    String SCHEMA = "CREATE TABLE users ( " + "id BIGINT NOT NULL AUTO_INCREMENT, " + "email VARCHAR(255), " + "username VARCHAR(255), " + "name VARCHAR(255), " + "password VARCHAR(255), " + "roles VARCHAR(255), " + "PRIMARY KEY (id) " + ");";

    @BeforeAll
    void setUp() {
        jdbcTemplate.execute(SCHEMA);

        userRepository.save(new User(null, "testuser01", passwordEncoder.encode("password"), "user"));
    }

    @Test
    void testRegisterUser() throws Exception {
        User user = new User();
        user.setUsername("testuser02");
        user.setPassword("password");

        mockMvc.perform(MockMvcRequestBuilders.post("/users/register").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(user))).andExpect(status().isOk());
    }

    @Test
    void testLoginUser() throws Exception {
        // Assume user is already registered

        AuthRequest authRequest = new AuthRequest();
        authRequest.setUsername("testuser01");
        authRequest.setPassword("password");

        mockMvc.perform(MockMvcRequestBuilders.post("/users/login").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(authRequest))).andExpect(status().isOk());
    }
}

