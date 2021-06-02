package com.desafiospring.DesafioSpring.repository;

import com.desafiospring.DesafioSpring.models.Post;
import org.springframework.http.ResponseEntity;

public interface PostRepository {
    Post getPost(int id);
    void addPost(Post post);
}
