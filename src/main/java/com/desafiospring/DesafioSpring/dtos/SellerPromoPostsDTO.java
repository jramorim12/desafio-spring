package com.desafiospring.DesafioSpring.dtos;

import com.desafiospring.DesafioSpring.models.Post;

import java.util.List;

public class SellerPromoPostsDTO {
    private int userId;
    private String userName;
    private List<Post> posts;

    public SellerPromoPostsDTO(int userId, String userName, List<Post> posts) {
        this.userId = userId;
        this.userName = userName;
        this.posts = posts;
    }

    public SellerPromoPostsDTO() {}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
