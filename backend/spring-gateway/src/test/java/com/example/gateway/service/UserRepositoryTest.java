package com.example.gateway.service;

import com.example.gateway.domain.User;
import com.example.gateway.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import reactor.test.StepVerifier;

@DataR2dbcTest
@TestPropertySource(properties = { "spring.config.location=classpath:application-test.yml" })
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    DatabaseClient client;

    @Autowired
    UserRepository userRepository;

    @Test
    void insert_user_test() {
        User user = new User("jinwoo", "test");
        userRepository.save(user);
        StepVerifier.create(userRepository.findByEmail("test")).expectSubscription().expectNext(user);
    }
}