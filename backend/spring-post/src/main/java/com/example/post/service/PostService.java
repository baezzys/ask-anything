package com.example.post.service;

import com.example.post.domain.Post;
import com.example.post.domain.User;
import com.example.post.dto.PostDto;
import com.example.post.repository.PostRepository;
import com.example.post.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
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
                .flatMap(user -> postRepository.save(Post.of(postDto, user)));
    }

    public Flux<Post> getPost(String userEmail, int page) {
        Mono<User> userMono = userRepository.findByEmail(userEmail);
        return userMono.flatMapMany(user -> {
            PageRequest pageRequest = PageRequest.of(page, 4);
            return postRepository.findAllByUserIdIs(user.getUserId(), pageRequest);
        });
    }
}
