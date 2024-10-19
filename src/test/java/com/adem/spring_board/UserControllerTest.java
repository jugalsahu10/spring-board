package com.adem.spring_board;

import com.adem.spring_board.dto.AuthRequest;
import com.adem.spring_board.model.User;
import com.adem.spring_board.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
//@Sql(scripts = "/schema.sql")
public class UserControllerTest {

    @Autowired private MockMvc mockMvc;

    @Autowired private UserRepository userRepository;  // You can also mock this if needed

    @Autowired private ObjectMapper objectMapper;  // To convert Java objects to JSON

    @Autowired private JdbcTemplate jdbcTemplate;  // Autowire JdbcTemplate

    @Autowired private PasswordEncoder passwordEncoder;


    String SCHEMA = "-- Table structure for table `users`\n" +
            "DROP TABLE IF EXISTS users;\n" +
            "\n" +
            "CREATE TABLE users (\n" +
            "  id BIGINT NOT NULL AUTO_INCREMENT,\n" +
            "  email VARCHAR(255) DEFAULT NULL,\n" +
            "  username VARCHAR(255) DEFAULT NULL,\n" +
            "  address VARCHAR(255) DEFAULT NULL,\n" +
            "  name VARCHAR(255) DEFAULT NULL,\n" +
            "  type TINYINT DEFAULT NULL,\n" +
            "  password VARCHAR(255) DEFAULT NULL,\n" +
            "  roles VARCHAR(255) DEFAULT NULL,\n" +
            "  PRIMARY KEY (id)\n" +
            ");\n" +
            "\n" +
            "-- Table structure for table `product`\n" +
            "DROP TABLE IF EXISTS product;\n" +
            "\n" +
            "CREATE TABLE product (\n" +
            "  id BIGINT NOT NULL AUTO_INCREMENT,\n" +
            "  name VARCHAR(255) DEFAULT NULL,\n" +
            "  price DOUBLE DEFAULT NULL,\n" +
            "  quantity INT DEFAULT NULL,\n" +
            "  PRIMARY KEY (id)\n" +
            ");\n" +
            "\n" +
            "-- Table structure for table `orders`\n" +
            "DROP TABLE IF EXISTS orders;\n" +
            "\n" +
            "CREATE TABLE orders (\n" +
            "  id BIGINT NOT NULL AUTO_INCREMENT,\n" +
            "  quantity INT NOT NULL,\n" +
            "  product_id BIGINT DEFAULT NULL,\n" +
            "  user_id BIGINT DEFAULT NULL,\n" +
            "  PRIMARY KEY (id),\n" +
            "  FOREIGN KEY (user_id) REFERENCES users(id),\n" +
            "  FOREIGN KEY (product_id) REFERENCES product(id)\n" +
            ");\n" +
            "\n" +
            "-- Dumping data for table `users`\n";

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute(SCHEMA);

        userRepository.save(new User(null, "username", passwordEncoder.encode("password"), "usedr"));
    }

    @Test
    void testRegisterUser() throws Exception {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");

        mockMvc.perform(MockMvcRequestBuilders.post("/users/register").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(user))).andExpect(status().isOk());
    }

    @Test
    void testLoginUser() throws Exception {
        // Assume user is already registered

        AuthRequest authRequest = new AuthRequest();
        authRequest.setUsername("testuser");
        authRequest.setPassword("password");

        mockMvc.perform(MockMvcRequestBuilders.post("/users/login").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(authRequest))).andExpect(status().isOk());
    }
}

