package com.desafiospring.DesafioSpring.repository;

import com.desafiospring.DesafioSpring.models.Post;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostRepository {
    Post getPost(int id);
    List<Post> getPost();
    void addPost(Post post);
    List<Post> sellerPosts(int idUser);
}
