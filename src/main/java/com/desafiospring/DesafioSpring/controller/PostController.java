package com.desafiospring.DesafioSpring.controller;

import com.desafiospring.DesafioSpring.models.Post;
import com.desafiospring.DesafioSpring.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/products/newpost")
    public ResponseEntity followedList(@RequestBody Post post){
        return postService.addPost(post);
    }
}
