package com.desafiospring.DesafioSpring.dtos;

import com.desafiospring.DesafioSpring.models.Product;
import com.desafiospring.DesafioSpring.utils.CustomJsonDateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class PostDTO {

    private int id_post;
    private int userId;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date date;
    private Product detail;
    private int category;
    private double price;
    private boolean hasPromo = false;
    private double discount = 0.0;

    public PostDTO(int id_post, int userId, Date date, Product detail, int category, double price, boolean hasPromo, double discount) {
        this.id_post = id_post;
        this.userId = userId;
        this.date = date;
        this.detail = detail;
        this.category = category;
        this.price = price;
        this.hasPromo = hasPromo;
        this.discount = discount;
    }

    public Product getDetail() {
        return detail;
    }

    public void setDetail(Product detail) {
        this.detail = detail;
    }

    public PostDTO() {}

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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    public Date getDate() {
        return date;
    }

    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    public void setDate(Date date) {
        this.date = date;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isHasPromo() {
        return hasPromo;
    }

    public void setHasPromo(boolean hasPromo) {
        this.hasPromo = hasPromo;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
