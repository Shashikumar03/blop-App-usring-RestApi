package com.example.blogapp.Dto;

import com.example.blogapp.entities.Category;
import com.example.blogapp.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class PostDto {
    private Integer id;
    private String title;
    private String content;
  //  private String imageName;
    private Date date;
    private CategoryDto category;
    private UserDto user;
}
