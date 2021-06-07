package com.desafiospring.DesafioSpring.dtos;

import com.desafiospring.DesafioSpring.models.Post;

import java.util.ArrayList;

public class FollowedSellersPostDTO {
    private int userId;
    private ArrayList<PostDTO> posts;

    public FollowedSellersPostDTO(int userId, ArrayList<PostDTO> posts) {
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

    public ArrayList<PostDTO> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<PostDTO> posts) {
        this.posts = posts;
    }
}
