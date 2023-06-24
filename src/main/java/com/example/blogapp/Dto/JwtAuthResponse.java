package com.example.blogapp.Dto;

import lombok.Data;

@Data
public class JwtAuthResponse {
    private String token;
    private  UserDto user;
}
