package com.example.blogapp.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {

    private Integer id;
    @NotEmpty
    @Size(min = 4, message = "userName must be greater than 4 char")
    private String name;
    @Email(message = "invalid email id")
    @NotEmpty(message = "invalid")
    private String email;
    @NotEmpty
    @Size(min = 3, max = 12, message = "passwprd must be 3 to 12 char")
    private String password;
    @NotEmpty
    private String about;

}
