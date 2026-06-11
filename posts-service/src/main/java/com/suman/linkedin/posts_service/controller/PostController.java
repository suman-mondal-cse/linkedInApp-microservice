package com.suman.linkedin.posts_service.controller;

import com.suman.linkedin.posts_service.dto.PostCreateRequestDto;
import com.suman.linkedin.posts_service.dto.PostDto;
import com.suman.linkedin.posts_service.service.PostsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
public class PostController {

    private final PostsService postsService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostCreateRequestDto postCreateRequestDto, HttpServletRequest httpServletRequest){
        PostDto createdPost = postsService.createPost(postCreateRequestDto, 1L);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long postId){
        PostDto postDto = postsService.getPostById(postId);
        return ResponseEntity.status(HttpStatus.OK).body(postDto);
    }

    @GetMapping("/users/{userId}/allPosts")
    public ResponseEntity<List<PostDto>> getAllPostsOfUser(@PathVariable Long userId){
        List<PostDto> posts = postsService.getAllPostsOfUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

}
