package com.example.post.service;

import com.example.post.domain.Post;
import com.example.post.domain.User;
import com.example.post.dto.PostDto;
import com.example.post.repository.PostRepository;
import com.example.post.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PostService {

    final PostRepository postRepository;

    final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Mono addPost(PostDto postDto, String userEmail) {
        return userRepository.findByEmail(userEmail)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("User not found")))
                .map(user -> ResponseEntity.ok(postRepository.save(Post.of(postDto, user))));
    }
}
