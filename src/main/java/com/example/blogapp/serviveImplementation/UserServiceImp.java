package com.example.blogapp.serviveImplementation;

import com.example.blogapp.Dto.UserDto;
import com.example.blogapp.Repository.RoleRepository;
import com.example.blogapp.Repository.UserRepository;
import com.example.blogapp.config.AppConst;
import com.example.blogapp.entities.Role;
import com.example.blogapp.entities.User;
import com.example.blogapp.exception.ApiException;
import com.example.blogapp.exception.GlobalExceptionHandler;
import com.example.blogapp.exception.ResourceNotFoundException;
import com.example.blogapp.service.UserService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.nio.channels.AlreadyBoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Validated
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RoleRepository roleRepository;
    private final Validator validator;
    public UserServiceImp(Validator validator) {
        this.validator = validator;
    }

    @Override
    public UserDto registerNewUser(UserDto userDto) {
        String email="";
        User user = modelMapper.map(userDto, User.class);
            email = user.getEmail();
            Boolean exist = userRepository.existsByEmail(email);
            if(exist){
                throw new ResourceNotFoundException("emsll","hjh",1);
            }

        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        //giving roles
        Role role = this.roleRepository.findById(AppConst.Normal_USER).get();
        user.getRoles().add(role);
        User user1 = this.userRepository.save(user);
        UserDto userDto1 = modelMapper.map(user, UserDto.class);

        return userDto1;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User saveUser=null;
        try{
            saveUser=userRepository.save(user);
        }catch (Exception e){
            throw  new ApiException("email is already prented");
        }

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
