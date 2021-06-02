package com.desafiospring.DesafioSpring.repository;

import com.desafiospring.DesafioSpring.models.Post;
import com.desafiospring.DesafioSpring.models.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Repository
public class PostRepositoryImpl implements PostRepository {

    private List<Post> loadJSON() {
        File file = null;
        List<Post> lista = null;

        try{
            file = ResourceUtils.getFile("classpath:post.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if(file.length() > 0) {
            ObjectMapper objectMapper = new ObjectMapper();
            TypeReference<List<Post>> typeReference = new TypeReference<>(){};

            try{
                lista = objectMapper.readValue(file, typeReference);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return lista;
    }

    private void writeJSON(List<Post> lista){
        try {
            File file = null;
            file = ResourceUtils.getFile("classpath:post.json");
            TypeReference<List<Post>> typeReference = new TypeReference<>(){};
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(file, lista);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Post getPost(int id) {
        List<Post> postList = loadJSON();
        Post post = postList.stream().filter(p -> p.getId_post() == id).findFirst().orElse(null);
        return post;
    }

    @Override
    public void addPost(Post post) {
        List<Post> postList = loadJSON();
        postList.add(post);
        writeJSON(postList);
    }
}
