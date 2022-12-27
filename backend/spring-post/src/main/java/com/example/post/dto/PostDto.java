package com.example.post.dto;

public class PostDto {
    private String title;

    private String text;

    public PostDto(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
