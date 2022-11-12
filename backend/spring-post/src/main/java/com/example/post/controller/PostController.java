package com.example.post.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    @PostMapping("/post")
    public String createPost() {
        System.out.println("Post is created");
        return "hello";
    }
}
