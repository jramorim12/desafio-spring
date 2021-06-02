package com.desafiospring.DesafioSpring.dtos;

import java.util.ArrayList;

public class FollowedListDTO {
    private int userId;
    private String userName;
    private ArrayList<UserInfoDTO> followed;

    public FollowedListDTO(int userId, String userName, ArrayList<UserInfoDTO> followed) {
        this.userId = userId;
        this.userName = userName;
        this.followed = followed;
    }

    public FollowedListDTO() {}

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

    public ArrayList<UserInfoDTO> getFollowed() {
        return followed;
    }

    public void setFollowed(ArrayList<UserInfoDTO> followed) {
        this.followed = followed;
    }
}
