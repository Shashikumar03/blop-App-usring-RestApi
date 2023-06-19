package com.example.blogapp.controller;

import com.example.blogapp.Dto.CategoryDto;
import com.example.blogapp.Dto.PostDto;
import com.example.blogapp.Repository.CategoryRepository;
import com.example.blogapp.Repository.PostRepository;
import com.example.blogapp.entities.Post;
import com.example.blogapp.response.ApiResponse;
import com.example.blogapp.serviveImplementation.CategoryServiceImp;
import com.example.blogapp.serviveImplementation.PostServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<PostDto>> getPodtByUser(@PathVariable Integer userId){
        List<PostDto> postDtos = postServiceImp.getAllPostByUser(userId);
        return new ResponseEntity<List<PostDto> >(postDtos,HttpStatus.OK);
    }
    @GetMapping("/category/{categoryId}/post")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
        List<PostDto> postDtos = postServiceImp.getAllPostByCategory(categoryId);
        return new ResponseEntity<List<PostDto> >(postDtos,HttpStatus.OK);
    }
    // getAllPost
    @GetMapping("/allPost")
    public ResponseEntity<List<PostDto>>getAllPost(
            @RequestParam(value = "pageNumber",defaultValue = "1",required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "5",required = false)Integer pageSize){

        List<PostDto> postDtos = postServiceImp.allPost(pageNumber, pageSize);
        return new ResponseEntity<List<PostDto> >(postDtos,HttpStatus.OK);
    }
    //get post by id
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto>getPostById(@PathVariable Integer postId) {
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
