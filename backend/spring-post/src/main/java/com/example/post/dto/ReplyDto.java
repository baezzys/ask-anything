package com.example.post.dto;

public class ReplyDto {

    private String content;

    public ReplyDto(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
