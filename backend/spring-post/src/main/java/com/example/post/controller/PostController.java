package com.example.post.controller;


import com.example.post.domain.Post;
import com.example.post.dto.PostDto;
import com.example.post.service.PostService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class PostController {

    final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/post")
    public ResponseEntity<Mono<Post>> createPost(@AuthenticationPrincipal OAuth2AuthenticatedPrincipal principal, @RequestBody PostDto postDto) {
        String userEmail = principal.getAttributes().get("email").toString();

        return ResponseEntity.ok(postService.addPost(postDto, userEmail));
    }

    @GetMapping("/post/{page}")
    public ResponseEntity<Flux<Post>> getPost(@AuthenticationPrincipal OAuth2AuthenticatedPrincipal principal, @PathVariable int page) {
        String userEmail = principal.getAttributes().get("email").toString();

        return ResponseEntity.ok(postService.getPost(userEmail, page));
    }
}
