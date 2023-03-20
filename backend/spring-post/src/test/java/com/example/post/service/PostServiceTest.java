package com.example.post.service;

import com.example.post.domain.Post;
import com.example.post.domain.User;
import com.example.post.dto.PostDto;
import com.example.post.repository.PostRepository;
import com.example.post.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;

@ContextConfiguration(initializers = PostServiceTest.EnvInitializer.class)
@DataR2dbcTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {PostService.class}))
@Testcontainers
public class PostServiceTest {

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

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

    @BeforeEach
    void addTestUser() {
        userRepository.save(new User("jinwoobae", "wlsdn3578@gmail.com")).block();
    }

    @Test
    void getPostTest() {
        for (int i = 0; i < 8; i++) {
            PostDto post = new PostDto("test", "test");
            postService.addPost(post, "wlsdn3578@gmail.com").block();
        }

        userRepository.findByEmail("wlsdn3578@gmail.com")
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();

        List<Post> postList = postRepository.findAll().collectList().block();

        System.out.println(postList.size());

        Flux<Post> postPage = postService.getPost("wlsdn3578@gmail.com", 0);

        StepVerifier.create(postPage)
                .expectNextCount(4)
                .expectComplete()
                .verify();
    }
}
