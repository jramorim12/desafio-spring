package com.desafiospring.DesafioSpring.repository;

import com.desafiospring.DesafioSpring.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public interface UserRepository {
    User getUser(int id);
    List<User> getUsers();
    void updateUser(User user);
    void addUser(User user);
    void deleteUser(int id);
}
