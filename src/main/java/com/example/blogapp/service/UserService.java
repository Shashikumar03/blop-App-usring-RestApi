package com.example.blogapp.service;

import com.example.blogapp.Dto.UserDto;

import java.util.List;

public interface UserService {

   UserDto createUser(UserDto user);
   UserDto updateUser(UserDto user, long id);
   UserDto getUserById(long UserId);
   List<UserDto> getAllUsers();
   void deleteUser(long UserId);

}
