package com.desafiospring.DesafioSpring.entity;

import java.util.Date;

public class VisitedPost {
    private int id_post;
    private int userId;
    private Date date;

    public VisitedPost(int id_post, int userId, Date date) {
        this.id_post = id_post;
        this.userId = userId;
        this.date = date;
    }

    public VisitedPost() {}

    public int getId_post() {
        return id_post;
    }

    public void setId_post(int id_post) {
        this.id_post = id_post;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
