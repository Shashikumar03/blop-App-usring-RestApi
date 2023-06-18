package com.example.blogapp.serviveImplementation;

import com.example.blogapp.Dto.UserDto;
import com.example.blogapp.Repository.UserRepository;
import com.example.blogapp.entities.User;
import com.example.blogapp.exception.ResourceNotFoundException;
import com.example.blogapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User saveUser = userRepository.save(user);
        return userToDto(saveUser);

    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User  user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "id", userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(user.getAbout());
        User saveUser = this.userRepository.save(user);
        return  this.userToDto(saveUser);

    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "userId", userId));
      return  this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> allUser = userRepository.findAll();
        List<UserDto> allDto= new ArrayList<>();
        for(User user:allUser){
            UserDto userDto = userToDto(user);
            allDto.add(userDto);
        }
        return allDto;
    }

    @Override
    public void deleteUser(Integer userId) {
        this.userRepository.findById(userId).orElseThrow(()-> new  ResourceNotFoundException("user","id", userId));
        this.userRepository.deleteById(userId);
    }
    public User dtoToUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());

        return user;
    }
   public  UserDto userToDto(User user) {
       UserDto userDto = this.modelMapper.map(user, UserDto.class);
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
       return userDto;
   }
}
