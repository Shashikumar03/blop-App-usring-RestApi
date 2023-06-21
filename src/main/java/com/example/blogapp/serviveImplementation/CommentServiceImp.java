package com.example.blogapp.serviveImplementation;

import com.example.blogapp.Dto.CommentDto;
import com.example.blogapp.Dto.PostDto;
import com.example.blogapp.Repository.CommentRepository;
import com.example.blogapp.Repository.PostRepository;
import com.example.blogapp.entities.Comment;
import com.example.blogapp.entities.Post;
import com.example.blogapp.exception.ResourceNotFoundException;
import com.example.blogapp.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImp implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(() ->
                new ResourceNotFoundException("Post", "postId", postId));

        Comment comment = modelMapper.map(commentDto, Comment.class);
         comment.setPost(post);

        Comment saveComment = this.commentRepository.save(comment);
        return this.modelMapper.map(saveComment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = this.commentRepository.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundException("comment", "id", commentId));
        this.commentRepository.delete(comment);

    }

    @Override
    public List<CommentDto> getAllComment() {
        List<Comment> comments = this.commentRepository.findAll();
        List<CommentDto> commentDtos = comments.stream().map((comment) -> this.modelMapper.map(comment, CommentDto.class))
                .collect(Collectors.toList());
        return commentDtos;
    }
}
