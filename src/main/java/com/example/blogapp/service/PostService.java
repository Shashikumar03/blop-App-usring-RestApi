package com.example.blogapp.service;

import com.example.blogapp.Dto.CategoryResponse;
import com.example.blogapp.Dto.PostDto;
import com.example.blogapp.Dto.PostResponse;
import com.example.blogapp.Dto.UserResponse;
import com.example.blogapp.entities.Post;

import java.util.List;

public interface PostService {
    //create
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    //update
    PostDto updatePost(PostDto postDto, Integer id);

    // delete
    void deletePost(Integer id);

    //getAllPost
    PostResponse allPost(Integer pageNo, Integer pageSize);

    //singlePost
    PostDto getPostById(Integer id);

    //getAllPostByCategory
    CategoryResponse getAllPostByCategory(Integer id, Integer pageNumber, Integer pageSize);

    // getAllPostByUser
    UserResponse getAllPostByUser(Integer id,Integer pageNo, Integer pageSize);

    //sreach post
    List<Post> searchPosts(String keywords);



}
