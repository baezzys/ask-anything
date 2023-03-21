package com.example.post.service;

import com.example.post.domain.Reply;
import com.example.post.repository.ReplyRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ReplyService {

    private final ReplyRepository replyRepository;

    public ReplyService(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    public Flux<Reply> getReply(long postId) {
        return replyRepository.findByPostId(postId);
    }
}
