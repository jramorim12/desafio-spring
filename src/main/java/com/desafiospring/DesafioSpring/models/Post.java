package com.desafiospring.DesafioSpring.models;

import com.desafiospring.DesafioSpring.utils.CustomJsonDateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Post {
    private int id_post;
    private int userId;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date date;
    private int productId;
    private int category;
    private double price;
    private boolean hasPromo = false;
    private double discount = 0.0;

    public Post(int id_post, int userId, Date date, int productId, int category, double price, boolean hasPromo, double discount) {
        this.id_post = id_post;
        this.userId = userId;
        this.date = date;
        this.productId = productId;
        this.category = category;
        this.price = price;
        this.hasPromo = hasPromo;
        this.discount = discount;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Post() {}

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
