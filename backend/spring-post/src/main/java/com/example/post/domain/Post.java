package com.example.post.domain;

import com.example.post.dto.PostDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Table("post")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @Column("post_id")
    private Long id;

    @Column("title")
    private String title;

    @Column("text")
    private String text;

    @Transient
    private List<Reply> replies = new ArrayList<>();

    @Column("user_id")
    private Long userId;

    @Transient
    private User user;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("modified_at")
    private LocalDateTime modifiedAt;

    public static Post of(PostDto postDto, User user) {
        return Post.builder()
                .text(postDto.getText())
                .title(postDto.getTitle())
                .userId(user.getUserId())
                .user(user)
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .build();
    }
}
