package com.example.blogapp.Dto;

import com.example.blogapp.entities.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.aspectj.bridge.IMessage;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {

    private Integer id;
    @NotEmpty(message ="name should not be empty")
    @NonNull
    @Size(min = 4, message = "userName must be greater than 4 char")
    private String name;

    @NotEmpty(message = "email is required")
    @Column(unique = true)
    @Email(message = "invalid email id")
    private String email;
    @NotEmpty
    @Size(min = 3, max = 12, message = "passwprd must be 3 to 12 char")
    private String password;
    @NotEmpty
    private String about;

    private Set<RoleDto> roles= new HashSet<>();

    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password=password;
    }


}
