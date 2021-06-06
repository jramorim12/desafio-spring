package com.desafiospring.DesafioSpring.service;

import com.desafiospring.DesafioSpring.models.Post;
import org.springframework.http.ResponseEntity;

public interface PostService {
    public ResponseEntity addPost(Post post);
    public ResponseEntity getFollowedSellersPosts(int idUser, String order);
    public ResponseEntity numberPromoPost(int idUser);
    public ResponseEntity getPromoPostsFromSeller(int idUser);
}
