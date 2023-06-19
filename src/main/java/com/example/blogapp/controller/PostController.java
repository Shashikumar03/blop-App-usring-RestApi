package com.example.blogapp.controller;

import com.example.blogapp.Dto.CategoryResponse;
import com.example.blogapp.Dto.PostDto;
import com.example.blogapp.Dto.PostResponse;
import com.example.blogapp.Dto.UserResponse;
import com.example.blogapp.Repository.PostRepository;
import com.example.blogapp.response.ApiResponse;
import com.example.blogapp.serviveImplementation.CategoryServiceImp;
import com.example.blogapp.serviveImplementation.PostServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class PostController {
    @Autowired
    private PostServiceImp postServiceImp;
    @Autowired
    private CategoryServiceImp categoryServiceImp;
    @Autowired
    private PostRepository postRepository;

    //create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer categoryId) {
        PostDto createdPostDto = postServiceImp.createPost(postDto, userId, categoryId);
        return new ResponseEntity<PostDto>(createdPostDto, HttpStatus.CREATED);
    }

    // get post by user
    @GetMapping("/user/{userId}/post")
    public ResponseEntity<UserResponse> getPodtByUser(
            @PathVariable Integer userId,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "3", required = false) Integer pageSize
    ) {
        UserResponse allPostByUser = postServiceImp.getAllPostByUser(userId, pageNumber, pageSize);
        return new ResponseEntity<UserResponse>(allPostByUser, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/post")
    public ResponseEntity<CategoryResponse> getPostByCategory(
            @PathVariable Integer categoryId,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {

        CategoryResponse allPostByCategory = postServiceImp.getAllPostByCategory(categoryId, pageNumber, pageSize);
        return new ResponseEntity<CategoryResponse>(allPostByCategory, HttpStatus.OK);
    }

    // getAllPost
    @GetMapping("/allPost")
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {

        PostResponse postResponse = postServiceImp.allPost(pageNumber, pageSize);
        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
    }

    //get post by id
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
        PostDto postDto = this.postServiceImp.getPostById(postId);
        return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
    }
    //delete post{
    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePostById(@PathVariable Integer postId){
        postServiceImp.deletePost(postId);
        return new ApiResponse("deleted ", true);
    }
    // update Post
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId){
        PostDto postDto1 = this.postServiceImp.updatePost(postDto,postId);
        return new ResponseEntity<PostDto>(postDto1,HttpStatus.OK);
    }

}
