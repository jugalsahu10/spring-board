package com.adem.spring_board;

import com.adem.spring_board.security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
    }

    @Test
    void testGenerateToken() {
        String token = jwtUtil.generateToken("testuser");
        assertNotNull(token);
    }

    @Test
    void testExtractUsername() {
        String token = jwtUtil.generateToken("testuser");
        String username = jwtUtil.extractUsername(token);
        assertEquals("testuser", username);
    }

    @Test
    void testValidateToken() {
        String token = jwtUtil.generateToken("testuser");
        assertTrue(jwtUtil.validateToken(token));
    }
}

