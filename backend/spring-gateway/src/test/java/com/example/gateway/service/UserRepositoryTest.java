package com.example.gateway.service;

import com.example.gateway.domain.User;
import com.example.gateway.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.test.StepVerifier;

@DataR2dbcTest
@ContextConfiguration(initializers = UserRepositoryTest.EnvInitializer.class)
@Testcontainers
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    static class EnvInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of(
                    String.format("spring.r2dbc.url=r2dbc:tc:mysql:///ask_anything?TC_IMAGE_TAG=latest&TC_INITSCRIPT=schema.sql"),
                    "spring.sql.init.mode=always",
                    "spring.r2dbc.username=root",
                    "spring.r2dbc.password=root"
            ).applyTo(applicationContext);
        }
    }

    @Test
    void insert_user_test() {

        userRepository.save(new User("jinwoobae", "wlsdn3578@gmail.com")).block();

        userRepository.findByEmail("wlsdn3578@gmail.com")
                        .as(StepVerifier::create)
                                .expectNextCount(1)
                                        .verifyComplete();
    }
}
