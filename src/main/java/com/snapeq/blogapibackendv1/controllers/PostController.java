package com.snapeq.blogapibackendv1.controllers;


import com.snapeq.blogapibackendv1.domain.dtos.CreatePostRequestDto;
import com.snapeq.blogapibackendv1.domain.dtos.PostDto;
import com.snapeq.blogapibackendv1.domain.dtos.UpdatePostRequestDto;
import com.snapeq.blogapibackendv1.domain.entities.CreatePostRequest;
import com.snapeq.blogapibackendv1.domain.entities.Post;
import com.snapeq.blogapibackendv1.domain.entities.UpdatePostRequest;
import com.snapeq.blogapibackendv1.domain.entities.User;
import com.snapeq.blogapibackendv1.mappers.PostMapper;
import com.snapeq.blogapibackendv1.services.PostService;
import com.snapeq.blogapibackendv1.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/blogapi/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;
    private final UserService userService;


    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        List<PostDto> postDtos = posts.stream().map(postMapper::toDto).toList();
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }

    @GetMapping(path = "/drafts")
    public ResponseEntity<List<PostDto>> getDrafts(@RequestAttribute UUID userId) {
        User loggedInUser = userService.getUserById(userId);
        List<Post> draftPosts = postService.getDraftPosts(loggedInUser);
        List<PostDto> postDtos = draftPosts.stream().map(postMapper::toDto).toList();
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PostDto>createPost(
            @Valid @RequestBody CreatePostRequestDto createPostRequestDto,
            @RequestAttribute UUID userId) {
        User loggedInUser = userService.getUserById(userId);
        CreatePostRequest createPostRequest = postMapper.toCreatePostRequest(createPostRequestDto);
        Post createdPost = postService.createPost(loggedInUser, createPostRequest);
        PostDto createdPostDto = postMapper.toDto(createdPost);
        return new ResponseEntity<>(createdPostDto, HttpStatus.CREATED);
    }


    @PutMapping(path = "/{id}")
    public ResponseEntity<PostDto> updatePost(
            @PathVariable UUID id,
            @Valid @RequestBody UpdatePostRequestDto updatePostRequestDto){
        UpdatePostRequest updatePostRequest = postMapper.toUpdatePostRequest(updatePostRequestDto);
        Post updatedPost = postService.updatePost(id, updatePostRequest);
        PostDto updatedPostDto = postMapper.toDto(updatedPost);
        return new ResponseEntity<>(updatedPostDto, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PostDto> getPost(
         @PathVariable UUID id
    ) {
        Post post = postService.getPost(id);
        PostDto postDto = postMapper.toDto(post);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void>deletePost(@PathVariable UUID id){
        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
