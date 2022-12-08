package com.example.blogapp.controller;

import com.example.blogapp.Dto.UserDto;
import com.example.blogapp.response.ApiResponse;
import com.example.blogapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // create user
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto createdUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }
    // ger userby id
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable long userId){
        UserDto userDto = this.userService.getUserById(userId);
       // return new ResponseEntity<>(userDto, HttpStatus.OK);
         return   ResponseEntity.ok(userDto);
    }
    // get AllUser
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUser(){
        List<UserDto> allUsers = this.userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
    // update
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable long userId){
        UserDto userDto1 = this.userService.updateUser(userDto, userId);
        return ResponseEntity.ok(userDto1);
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> getUser(@PathVariable long userId){
        this.userService.deleteUser(userId);
        //return new ResponseEntity(Map.of("message", "delete Succesfully"), HttpStatus.OK);
        return new   ResponseEntity<ApiResponse>( new ApiResponse("user deleted Succesfully ",true), HttpStatus.OK);
    }
}
