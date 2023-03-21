package com.example.post.service;

import com.example.post.domain.Post;
import com.example.post.domain.Reply;
import com.example.post.domain.User;
import com.example.post.dto.PostDto;
import com.example.post.dto.ReplyDto;
import com.example.post.repository.PostRepository;
import com.example.post.repository.ReplyRepository;
import com.example.post.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PostService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    private final ReplyRepository replyRepository;



    public PostService(PostRepository postRepository, UserRepository userRepository, ReplyRepository replyRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.replyRepository = replyRepository;
    }

    public Mono<Post> addPost(PostDto postDto, String userEmail) {
        return userRepository.findByEmail(userEmail)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("User not found")))
                .flatMap(user -> postRepository.save(Post.of(postDto, user)));
    }

    public Flux<Post> getPost(String userEmail, int page) {
        Mono<User> userMono = userRepository.findByEmail(userEmail);
        return userMono.flatMapMany(user -> {
            PageRequest pageRequest = PageRequest.of(page, 10);
            return postRepository.findAllByUserIdIs(user.getUserId(), pageRequest);
        });
    }

    public Mono<Post> addReplyToPost(Long postId, ReplyDto replyDto) {
        return postRepository.findById(postId)
                .flatMap(post -> {
                    Reply reply = Reply.builder()
                            .content(replyDto.getContent())
                            .postId(postId)
                            .build();
                    return replyRepository.save(reply)
                            .doOnSuccess(savedReply -> post.getReplies().add(savedReply))
                            .thenReturn(post);
                });
    }
}
