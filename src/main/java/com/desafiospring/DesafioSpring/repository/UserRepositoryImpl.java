package com.desafiospring.DesafioSpring.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;
import com.desafiospring.DesafioSpring.entity.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private List<User> loadJSON() {
        File file = null;
        List<User> lista = null;

        try{
            file = ResourceUtils.getFile("classpath:user.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if(file.length() > 0) {
            ObjectMapper objectMapper = new ObjectMapper();
            TypeReference<List<User>> typeReference = new TypeReference<>(){};

            try{
                lista = objectMapper.readValue(file, typeReference);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return lista;
    }

    private void writeJSON(List<User> lista){
        try {
            File file = null;
            file = ResourceUtils.getFile("classpath:user.json");
            TypeReference<List<User>> typeReference = new TypeReference<>(){};
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(file, lista);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(int id){
        List<User> userList = loadJSON();
        User user = userList.stream().filter(u -> u.getUserId() == id).findFirst().orElse(null);
        return user;
    }

    @Override
    public void updateUser(User user) {
        deleteUser(user.getUserId());
        addUser(user);
    }

    @Override
    public void addUser(User user) {
        List<User> listaUser = loadJSON();
        listaUser.add(user);
        writeJSON(listaUser);
    }

    @Override
    public void deleteUser(int id) {
        List<User> listaUser = loadJSON();
        listaUser = listaUser.stream().filter(p -> p.getUserId() != id).collect(Collectors.toList());
        writeJSON(listaUser);
    }

}
