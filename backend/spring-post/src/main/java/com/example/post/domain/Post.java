package com.example.post.domain;

import com.example.post.dto.PostDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;


@Table("post")
@Builder
@Data
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
