package com.suman.linkedin.posts_service.service;

import com.suman.linkedin.posts_service.auth.UserContextHolder;
import com.suman.linkedin.posts_service.clients.ConnectionsClient;
import com.suman.linkedin.posts_service.dto.PersonDto;
import com.suman.linkedin.posts_service.dto.PostCreateRequestDto;
import com.suman.linkedin.posts_service.dto.PostDto;
import com.suman.linkedin.posts_service.entity.Post;
import com.suman.linkedin.posts_service.exception.ResourceNotFoundException;
import com.suman.linkedin.posts_service.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostsService {
    private final PostsRepository postsRepository;
    private final ModelMapper modelMapper;
    private final ConnectionsClient connectionsClient;

    public PostDto createPost(PostCreateRequestDto postCreateRequestDto, Long userId) {
        Post post = modelMapper.map(postCreateRequestDto, Post.class);
        post.setUserId(userId);

        Post savedPost = postsRepository.save(post);
        return modelMapper.map(savedPost, PostDto.class);
    }

    public PostDto getPostById(Long postId) {
        log.debug("Retrieving post with ID: {}", postId);
        Long userId = UserContextHolder.getCurrentUserId();

        List<PersonDto> firstConnections = connectionsClient.getFirstConnections();
        System.out.println(firstConnections);

//         TODO send notification to all connections.

        Post post = postsRepository.findById(postId).orElseThrow(()->
                new ResourceNotFoundException("post not found with id: "+postId));
        return modelMapper.map(post, PostDto.class);
    }

    public List<PostDto> getAllPostsOfUser(Long userId) {
        List<Post> posts = postsRepository.findByUserId(userId);

        List<PostDto> listOfPosts = posts.stream()
                .map((element)-> modelMapper.map(element, PostDto.class))
                .collect(Collectors.toList());

        return  listOfPosts;
    }
}
