package com.example.blogapp.service;

import com.example.blogapp.Dto.PostDto;
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
    List<PostDto> allPost();

    //singlePost
    PostDto getPostById(Integer id);

    //getAllPostByCategory
    List<PostDto> getAllPostByCategory(Integer id);

    // getAllPostByUser
    List<PostDto> getAllPostByUser(Integer id);

    //sreach post
    List<Post> searchPosts(String keywords);



}
