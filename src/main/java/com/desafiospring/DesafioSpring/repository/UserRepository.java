package com.desafiospring.DesafioSpring.repository;

import com.desafiospring.DesafioSpring.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserRepository {
    User getUser(int id);
    void updateUser(User user);
    void addUser(User user);
    void deleteUser(int id);
}
