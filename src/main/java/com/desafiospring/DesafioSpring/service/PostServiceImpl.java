package com.desafiospring.DesafioSpring.service;

import com.desafiospring.DesafioSpring.dtos.FollowedSellersPostDTO;
import com.desafiospring.DesafioSpring.dtos.PromoPostCounterDTO;
import com.desafiospring.DesafioSpring.dtos.SellerPromoPostsDTO;
import com.desafiospring.DesafioSpring.models.Post;
import com.desafiospring.DesafioSpring.models.User;
import com.desafiospring.DesafioSpring.repository.PostRepository;
import com.desafiospring.DesafioSpring.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{
    private PostRepository postRepository;
    private UserRepository userRepository;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    private void zeroTime(Calendar calendar){
        calendar.set( Calendar.HOUR_OF_DAY, 0 );
        calendar.set( Calendar.MINUTE, 0 );
        calendar.set( Calendar.SECOND, 0 );
        calendar.set( Calendar.MILLISECOND, 0 );
    }


    private boolean orderPostList(List<Post> postsList, String order){
        if(order.equals("date_asc")) {
            postsList.sort((o1, o2) -> o1.getDate().compareTo(o2.getDate()));
        }else if(order.equals("date_desc")){
            postsList.sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));
        }else{
            return false;
        }
        return true;
    }

    private List<Post> getSellerPosts(int idUser){
        List<Post> postsUser = new ArrayList<>();
        List<Post> posts = postRepository.getPost();
        return posts;
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

    @Override
    public ResponseEntity getFollowedSellersPosts(int idUser, String order) {
        User user = userRepository.getUser(idUser);
        Calendar calendar = Calendar.getInstance();
        zeroTime(calendar);
        Date nowDate = calendar.getTime();
        calendar.add(Calendar.DATE, -14);
        Date twoWeekAgoDate = calendar.getTime();

        if(user == null){
            return new ResponseEntity<String>("ID de usuário inexistente.", HttpStatus.BAD_REQUEST);
        }
        List<Post> postsList = new ArrayList<>();
        for(Integer id : user.getFollowingList()){
            postsList.addAll(postRepository.sellerPosts(id));
        }
        postsList = postsList.stream().filter(p -> nowDate.compareTo(p.getDate()) * p.getDate().compareTo(twoWeekAgoDate) > -1 ).collect(Collectors.toList());

        if(order != null){
            if(!orderPostList(postsList, order)){
                return new ResponseEntity<String>("Valor do parâmetro 'order' inválido. Insira 'name_asc' ou 'name_desc'.", HttpStatus.BAD_REQUEST);
            }
        }else{
            postsList.sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));
        }

        FollowedSellersPostDTO followedSellersPostDTO = new FollowedSellersPostDTO(idUser, (ArrayList<Post>) postsList);
        return new ResponseEntity<FollowedSellersPostDTO>(followedSellersPostDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity numberPromoPost(int idUser) {
        User user = userRepository.getUser(idUser);
        List<Post> postList = postRepository.sellerPosts(idUser);

        if(user == null)
            return new ResponseEntity<String>("Nenhum usuário foi encontrado com esse ID.", HttpStatus.BAD_REQUEST);
        if(!user.getSeller())
            return new ResponseEntity<String>("Usuário não é um vendedor. Insira o ID de um vendedor.", HttpStatus.BAD_REQUEST);

        PromoPostCounterDTO promoPostCounterDTO = new PromoPostCounterDTO();
        promoPostCounterDTO.setUserId(user.getUserId());
        promoPostCounterDTO.setUserName(user.getUserName());
        promoPostCounterDTO.setPromoproducts_count((int) postList.stream().filter(p -> p.isHasPromo()).count());

        return new ResponseEntity<PromoPostCounterDTO>(promoPostCounterDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getPromoPostsFromSeller(int idUser) {
        User user = userRepository.getUser(idUser);
        List<Post> postList = postRepository.sellerPosts(idUser);

        if(user == null)
            return new ResponseEntity<String>("Nenhum usuário foi encontrado com esse ID.", HttpStatus.BAD_REQUEST);
        if(!user.getSeller())
            return new ResponseEntity<String>("Usuário não é um vendedor. Insira o ID de um vendedor.", HttpStatus.BAD_REQUEST);

        SellerPromoPostsDTO sellerPromoPostsDTO = new SellerPromoPostsDTO();
        sellerPromoPostsDTO.setUserId(user.getUserId());
        sellerPromoPostsDTO.setUserName(user.getUserName());
        sellerPromoPostsDTO.setPosts((postList.stream().filter(p -> p.isHasPromo()).collect(Collectors.toList())));

        return new ResponseEntity<SellerPromoPostsDTO>(sellerPromoPostsDTO, HttpStatus.OK);
    }


}
