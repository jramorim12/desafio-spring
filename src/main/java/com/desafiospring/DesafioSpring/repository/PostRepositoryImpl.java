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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PostRepositoryImpl implements PostRepository {

    private final String pathPost = "./src/main/java/com/desafiospring/DesafioSpring/json/post.json";

    private List<Post> loadJSON() {
        File file = new File(pathPost);
        List<Post> lista = null;
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
            File file = new File(pathPost);
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
        if (postList == null)
            return null;
        Post post = postList.stream().filter(p -> p.getId_post() == id).findFirst().orElse(null);
        return post;
    }

    @Override
    public List<Post> getPost() {
        return loadJSON();
    }

    @Override
    public void addPost(Post post) {
        List<Post> postList = loadJSON();
        if(postList == null)
            postList = new ArrayList<>();
        postList.add(post);
        writeJSON(postList);
    }

    @Override
    public List<Post> sellerPosts(int idUser) {
        List<Post> postList = loadJSON();
        if(postList == null)
            return null;
        return postList.stream().filter(post -> post.getUserId() == idUser).collect(Collectors.toList());
    }
}
