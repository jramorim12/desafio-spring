package com.desafiospring.DesafioSpring.service;

import com.desafiospring.DesafioSpring.models.Post;
import org.springframework.http.ResponseEntity;

public interface PostService {
    public ResponseEntity addPost(Post post);
}
