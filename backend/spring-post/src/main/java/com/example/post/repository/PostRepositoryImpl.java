package com.example.post.repository;

import com.example.post.domain.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public class PostRepositoryImpl {

    private final DatabaseClient databaseClient;

    public PostRepositoryImpl(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

}
