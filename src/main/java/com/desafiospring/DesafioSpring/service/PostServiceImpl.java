package com.desafiospring.DesafioSpring.service;

import com.desafiospring.DesafioSpring.models.Post;
import com.desafiospring.DesafioSpring.repository.PostRepository;
import com.desafiospring.DesafioSpring.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService{
    private PostRepository postRepository;
    private UserRepository userRepository;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity addPost(Post post) {
        if(postRepository.getPost(post.getId_post()) != null)
            return new ResponseEntity<String>("Já existe um POST com esse ID.", HttpStatus.BAD_REQUEST);
        if(userRepository.getUser(post.getUserId()) == null)
            return new ResponseEntity<String>("ID de usuário inexistente.", HttpStatus.BAD_REQUEST);
        if(!userRepository.getUser(post.getUserId()).getSeller())
            return new ResponseEntity<String>("Usuário com o ID "+ post.getUserId() +" não é um vendedor.", HttpStatus.BAD_REQUEST);
        postRepository.addPost(post);
        return new ResponseEntity<String>("Post inserido com sucesso.", HttpStatus.OK);
    }
}
