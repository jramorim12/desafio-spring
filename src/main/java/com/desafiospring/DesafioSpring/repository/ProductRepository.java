package com.desafiospring.DesafioSpring.repository;

import com.desafiospring.DesafioSpring.models.Product;

public interface ProductRepository {
    Product getProduct(int id);
    void addProduct(Product product);
}
