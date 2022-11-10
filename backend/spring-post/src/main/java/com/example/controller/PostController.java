package com.example.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
public class PostController {


    @PostMapping("/post")
    public void createPost() {
        System.out.println("Post is created");
    }
}
