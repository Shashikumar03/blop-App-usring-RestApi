package com.example.blogapp.Dto;

import lombok.Data;

@Data
public class JwtAuthRequest {
    private String username;
    private String password;
}
