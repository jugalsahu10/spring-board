package com.adem.spring_board.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
