package com.example.post.service;

import com.example.post.domain.Post;
import com.example.post.domain.Reply;
import com.example.post.domain.User;
import com.example.post.dto.PostDto;
import com.example.post.dto.ReplyDto;
import com.example.post.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ContextConfiguration(initializers = ReplyServiceTest.EnvInitializer.class)
@DataR2dbcTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {ReplyService.class, PostService.class}))
@Testcontainers
public class ReplyServiceTest {

    @Autowired
    private ReplyService replyService;

    @Autowired
    private PostService postService;

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
    void getReplyTest() {
        PostDto postDto = new PostDto("test", "test");
        Post createdPost = postService.addPost(postDto, "wlsdn3578@gmail.com").block();

        ReplyDto replyDto = new ReplyDto("test");

        Post updatedPost = postService.addReplyToPost(createdPost.getId(), replyDto).block();

        List<Reply> replies = updatedPost.getReplies();

        assertNotNull(replies);
        assertEquals(1, replies.size());
        assertEquals("test", replies.get(0).getContent());

    }


}
