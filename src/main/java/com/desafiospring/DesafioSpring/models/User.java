package com.desafiospring.DesafioSpring.models;

import java.util.ArrayList;

public class User {
    private int userId;
    private String userName;
    private ArrayList<Integer> followingList;
    private Boolean seller;

    public User(int userId, String userName, ArrayList<Integer> followingList, Boolean seller) {
        this.userId = userId;
        this.userName = userName;
        this.followingList = followingList;
        this.seller = seller;
    }

    public User() {}

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

    public ArrayList<Integer> getFollowingList() {
        return followingList;
    }

    public void setFollowingList(ArrayList<Integer> followingList) {
        this.followingList = followingList;
    }

    public Boolean getSeller() {
        return seller;
    }

    public void setSeller(Boolean seller) {
        this.seller = seller;
    }
}
