package com.example.post.repository;

import com.example.post.domain.Post;
import com.example.post.domain.Reply;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

@EnableR2dbcRepositories
public interface ReplyRepository extends ReactiveCrudRepository<Reply, Long> {
    Flux<Reply> findByPostId(Long postId);
}
