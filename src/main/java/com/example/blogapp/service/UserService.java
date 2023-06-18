package com.example.blogapp.service;

import com.example.blogapp.Dto.UserDto;

import java.util.List;

public interface UserService {

   UserDto createUser(UserDto user);
   UserDto updateUser(UserDto user, Integer id);
   UserDto getUserById(Integer UserId);
   List<UserDto> getAllUsers();
   void deleteUser(Integer UserId);

}
