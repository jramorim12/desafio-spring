package com.desafiospring.DesafioSpring.dtos;

import com.desafiospring.DesafioSpring.models.Post;

import java.util.ArrayList;

public class FollowedSellersPostDTO {
    private int userId;
    private ArrayList<Post> posts;

    public FollowedSellersPostDTO(int userId, ArrayList<Post> posts) {
        this.userId = userId;
        this.posts = posts;
    }

    public FollowedSellersPostDTO() {}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }
}
