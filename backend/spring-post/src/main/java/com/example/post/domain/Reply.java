package com.example.post.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("reply")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Reply {

    @Id
    private Long id;
    private String content;

    @Column("post_id")
    private Long postId;
}
