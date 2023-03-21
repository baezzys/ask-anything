package com.example.post.repository;

import com.example.post.domain.User;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.Optional;

@EnableR2dbcRepositories

public interface UserRepository extends ReactiveCrudRepository<User, Long> {
    Mono<User> findByEmail(String userEmail);
}
