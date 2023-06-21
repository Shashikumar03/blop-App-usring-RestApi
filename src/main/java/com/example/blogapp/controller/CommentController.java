package com.example.blogapp.controller;

import com.example.blogapp.Dto.CommentDto;
import com.example.blogapp.response.ApiResponse;
import com.example.blogapp.serviveImplementation.CommentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {
    @Autowired
    private CommentServiceImp commentServiceImp;
    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId){
        CommentDto comment = this.commentServiceImp.createComment(commentDto, postId);
        return new ResponseEntity<CommentDto>(comment, HttpStatus.CREATED);

    }
    @DeleteMapping("//comments/{commentId}")
    public ApiResponse deleteComment(@PathVariable Integer commentId){
        this.commentServiceImp.deleteComment(commentId);
        return  new ApiResponse("deleted", true);

    }
    @GetMapping("/allComments")
    public ResponseEntity<List<CommentDto>> getAllComment(){
        System.out.println("bff");
        List<CommentDto> allComment = this.commentServiceImp.getAllComment();
        return new ResponseEntity<List<CommentDto>>(allComment, HttpStatus.OK);
    }
}
