package com.example.post.controller;

import com.example.post.domain.Post;
import com.example.post.dto.ReplyDto;
import com.example.post.service.PostService;
import com.example.post.service.ReplyService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ReplyController {

    private final ReplyService replyService;

    private final PostService postService;

    public ReplyController(ReplyService replyService, PostService postService) {
        this.replyService = replyService;
        this.postService = postService;
    }

    @PostMapping
    public Mono<Post> addReply(@PathVariable Long postId, @RequestBody ReplyDto replyDto) {
        return postService.addReplyToPost(postId, replyDto);
    }
}
