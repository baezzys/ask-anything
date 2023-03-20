package com.example.post.dto;

public class PostDto {
    private String title;

    private String text;

    private Long parentId;

    public PostDto(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public PostDto(String title, String text, Long parentId) {
        this.title = title;
        this.text = text;
        this.parentId = parentId;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public Long getParentId() {
        return parentId;
    }
}
