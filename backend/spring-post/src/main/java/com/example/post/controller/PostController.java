package com.example.post.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class PostController {

    @PostMapping("/post")
    public Mono<String> createPost(@RequestHeader HttpHeaders headers, @AuthenticationPrincipal OAuth2AuthenticatedPrincipal principal) {
        return Mono.just(principal.getAttributes().get("name").toString());
    }
}
