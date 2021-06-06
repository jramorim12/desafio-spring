package com.desafiospring.DesafioSpring.controller;

import com.desafiospring.DesafioSpring.models.Post;
import com.desafiospring.DesafioSpring.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


    @GetMapping("/products/followed/{idUser}/list")
    public ResponseEntity followedSellerPosts(@PathVariable int idUser, @RequestParam(value = "", required = false) String order){
        return postService.getFollowedSellersPosts(idUser, order);
    }

    @GetMapping("/products/{userId}/countPromo/")
    public ResponseEntity numberPromoPost(@PathVariable int userId){
        return postService.numberPromoPost(userId);
    }

    @PostMapping("/products/newpost")
    public ResponseEntity followedList(@RequestBody Post post){
        return postService.addPost(post);
    }


    @PostMapping("products/newpromopost")
    public ResponseEntity addPost(@RequestBody Post post){
        return postService.addPost(post);
    }

    @GetMapping("/products/{idUser}/list/")
    public ResponseEntity promoPostsFromSeller(@PathVariable int idUser){
        return postService.getPromoPostsFromSeller(idUser);
    }
}
