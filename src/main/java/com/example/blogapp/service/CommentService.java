package com.example.blogapp.service;

import com.example.blogapp.Dto.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, Integer postId);

    public  void deleteComment(Integer commentId);

    public List<CommentDto> getAllComment();
}
