package com.example.post.repository;

import com.example.post.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.xml.crypto.Data;
import java.util.Optional;

@EnableR2dbcRepositories
public interface PostRepository extends ReactiveCrudRepository<Post, Long> {
    Mono<Post> findById(long postId);

    Flux<Post> findAllByUserIdIs(Long userId, Pageable pageable);

}
