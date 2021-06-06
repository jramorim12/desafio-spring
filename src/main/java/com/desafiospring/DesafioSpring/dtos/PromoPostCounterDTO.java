package com.desafiospring.DesafioSpring.dtos;

public class PromoPostCounterDTO {
    private int userId;
    private String userName;
    private int promoproducts_count;

    public PromoPostCounterDTO(int userId, String userName, int promoproducts_count) {
        this.userId = userId;
        this.userName = userName;
        this.promoproducts_count = promoproducts_count;
    }

    public PromoPostCounterDTO() {}

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

    public int getPromoproducts_count() {
        return promoproducts_count;
    }

    public void setPromoproducts_count(int promoproducts_count) {
        this.promoproducts_count = promoproducts_count;
    }
}
