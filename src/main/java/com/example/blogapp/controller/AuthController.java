package com.example.blogapp.controller;

import com.example.blogapp.Dto.JwtAuthRequest;
import com.example.blogapp.Dto.JwtAuthResponse;
import com.example.blogapp.Dto.UserDto;
import com.example.blogapp.Repository.UserRepository;
import com.example.blogapp.entities.User;
import com.example.blogapp.exception.ApiException;
import com.example.blogapp.security.JwtTokenHelper;
import com.example.blogapp.serviveImplementation.UserServiceImp;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
    @Autowired
  private JwtTokenHelper jwtTokenHelper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserServiceImp userServiceImp;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse>createToken(@RequestBody JwtAuthRequest request) throws Exception {
     // System.out.println(request.getUsername());
       this.authenticate(request.getUsername(), request.getPassword());
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
      System.out.println(userDetails);
      String token = this.jwtTokenHelper.generateToken(userDetails);
      System.out.println("token"+token);
      JwtAuthResponse response= new JwtAuthResponse();
      response.setToken(token);
      response.setUser(this.mapper.map((User) userDetails, UserDto.class));
      return  new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
    }

    private  void authenticate(String username, String password) throws Exception {
      UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(username, password);
      try{
        this.authenticationManager.authenticate(authenticationToken);
      }catch (BadCredentialsException e){
        System.out.println(e);
        throw new ApiException("invalid user name ");
      }
    }
    //register new user
  @PostMapping("/register")
  public  ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto){
    UserDto registerUserDto = this.userServiceImp.registerNewUser(userDto);
    return  new ResponseEntity<UserDto>(registerUserDto, HttpStatus.CREATED);
  }

  @Autowired
  private UserRepository userRepo;
  @Autowired
  private ModelMapper mapper;

  @GetMapping("/current-user/")
  public ResponseEntity<UserDto> getUser(Principal principal) {
    User user = this.userRepo.findByEmail(principal.getName()).get();
    return new ResponseEntity<UserDto>(this.mapper.map(user, UserDto.class), HttpStatus.OK);
  }
}
