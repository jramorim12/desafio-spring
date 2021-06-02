package com.desafiospring.DesafioSpring.dtos;

import java.util.ArrayList;

public class FollowersListDTO {
    private int userId;
    private String userName;
    private ArrayList<UserInfoDTO> followers;

    public FollowersListDTO() {}

    public FollowersListDTO(int userId, String userName, ArrayList<UserInfoDTO> followersList) {
        this.userId = userId;
        this.userName = userName;
        this.followers = followersList;
    }

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

    public ArrayList<UserInfoDTO> getFollowers() {
        return followers;
    }

    public void setFollowers(ArrayList<UserInfoDTO> followers) {
        this.followers = followers;
    }
}
