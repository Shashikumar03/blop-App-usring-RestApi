package com.example.blogapp.serviveImplementation;

import com.example.blogapp.Dto.CategoryResponse;
import com.example.blogapp.Dto.PostDto;
import com.example.blogapp.Dto.PostResponse;
import com.example.blogapp.Dto.UserResponse;
import com.example.blogapp.Repository.CategoryRepository;
import com.example.blogapp.Repository.PostRepository;
import com.example.blogapp.Repository.UserRepository;
import com.example.blogapp.entities.Category;
import com.example.blogapp.entities.Comment;
import com.example.blogapp.entities.Post;
import com.example.blogapp.entities.User;
import com.example.blogapp.exception.ResourceNotFoundException;
import com.example.blogapp.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostServiceImp implements PostService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId ){
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", "id", categoryId));
        Post post = this.modelMapper.map(postDto, Post.class);
        System.out.println(user);

      //  post.setImageName("shashi.png");
        post.setDate( new Date());
        post.setCategory(category);
        post.setUser(user);
        Post newPost = postRepository.save(post);
        return this.modelMapper.map(newPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
    Post post= postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "id", postId));
    post.setContent(postDto.getContent());
    post.setTitle(postDto.getTitle());
        Post post1 = this.postRepository.save(post);
        PostDto updatedPost = this.modelMapper.map(post1, PostDto.class);
        return updatedPost;
    }

    @Override
    public void deletePost(Integer id) {

        Post post = this.postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
        this.postRepository.delete(post);

    }

    @Override
    public PostResponse allPost(Integer pageNumber, Integer pageSize,String sortBy, String sortDir) {
        Sort sort= sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        Pageable page= PageRequest.of(pageNumber, pageSize,sort);
      //  Pageable page= PageRequest.of(pageNumber, pageSize,Sort.by(sortBy).ascending());
        Page<Post> pagePost = postRepository.findAll(page);
        List<Post> posts = pagePost.getContent();
        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

        PostResponse postResponse= new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElement(pagePost.getNumberOfElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer id) {
        Post post = this.postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));

        return   this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public CategoryResponse getAllPostByCategory(Integer id, Integer pageNumber, Integer pageSize) {

        Pageable page = PageRequest.of(pageNumber, pageSize);
        Category cat = this.categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("category", "cat id", id));
        Page<Post> pagePost = postRepository.findByCategory(cat, page);
        List<Post> posts = pagePost.getContent();
        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(postDtos);
        categoryResponse.setPageNumber(pagePost.getNumber());
        categoryResponse.setLastPage(pagePost.isLast());
        categoryResponse.setTotalElement(pagePost.getTotalElements());
        categoryResponse.setTotalPages(pagePost.getTotalPages());
        return categoryResponse;
    }

    @Override
    public UserResponse getAllPostByUser(Integer id,Integer pageNumber, Integer pageSize) {
        Pageable page=PageRequest.of(pageNumber,pageSize);

        User user = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("usr", "userId", id));
        Page<Post> userPost = this.postRepository.findByUser(user,page);

        List<Post> posts = userPost.getContent();
        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        UserResponse userResponse= new UserResponse();
        userResponse.setContent(postDtos);
        userResponse.setTotalPages(userPost.getTotalPages());
        userResponse.setLastPage(userPost.isLast());
       // userResponse.setTotalElement();
        userResponse.setPageSize(userPost.getSize());
        userResponse.setPageNumber(userPost.getNumber());
        userResponse.setTotalElement(userPost.getTotalElements());

        return  userResponse;
    }

    @Override
    public List<PostDto> searchPosts(String keywords) {
        List<Post>posts = this.postRepository.findByTitleContaining(keywords);
        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }
}
