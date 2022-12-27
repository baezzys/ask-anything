package com.example.post.controller;


import com.example.post.dto.PostDto;
import com.example.post.service.PostService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class PostController {

    final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/post")
    public Mono<ResponseEntity> createPost(@RequestHeader HttpHeaders headers, @AuthenticationPrincipal OAuth2AuthenticatedPrincipal principal, @RequestBody PostDto postDto) {
        String userEmail = principal.getAttributes().get("email").toString();

        return postService.addPost(postDto, userEmail);
    }
}
