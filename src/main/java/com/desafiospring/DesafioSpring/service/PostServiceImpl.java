package com.desafiospring.DesafioSpring.service;

import com.desafiospring.DesafioSpring.dtos.FollowedSellersPostDTO;
import com.desafiospring.DesafioSpring.dtos.PostDTO;
import com.desafiospring.DesafioSpring.dtos.PromoPostCounterDTO;
import com.desafiospring.DesafioSpring.dtos.SellerPromoPostsDTO;
import com.desafiospring.DesafioSpring.models.Post;
import com.desafiospring.DesafioSpring.models.User;
import com.desafiospring.DesafioSpring.repository.PostRepository;
import com.desafiospring.DesafioSpring.repository.ProductRepository;
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
    private ProductRepository productRepository;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    private void zeroTime(Calendar calendar){
        calendar.set( Calendar.HOUR_OF_DAY, 0 );
        calendar.set( Calendar.MINUTE, 0 );
        calendar.set( Calendar.SECOND, 0 );
        calendar.set( Calendar.MILLISECOND, 0 );
    }

    private Post dtoToPost(PostDTO postDTO){
        if(postDTO == null)
            return null;

        Post post = new Post();

        post.setId_post(postDTO.getId_post());
        post.setUserId(postDTO.getUserId());
        post.setDate(postDTO.getDate());
        post.setProductId(postDTO.getDetail().getProduct_id());
        post.setCategory(postDTO.getCategory());
        post.setPrice(postDTO.getPrice());
        post.setHasPromo(postDTO.isHasPromo());
        post.setDiscount(postDTO.getDiscount());

        return post;
    }

    private PostDTO postToDTO(Post post){

        if(post == null)
            return null;

        PostDTO postDTO = new PostDTO();

        postDTO.setId_post(post.getId_post());
        postDTO.setUserId(post.getUserId());
        postDTO.setDate(post.getDate());
        postDTO.setDetail(productRepository.getProduct(post.getProductId()));
        postDTO.setCategory(post.getCategory());
        postDTO.setPrice(post.getPrice());
        postDTO.setHasPromo(post.isHasPromo());
        postDTO.setDiscount(post.getDiscount());

        return postDTO;
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
    public ResponseEntity addPost(PostDTO post) {

        if(postRepository.getPost(post.getId_post()) != null)
            return new ResponseEntity<String>("Já existe um POST com esse ID.", HttpStatus.BAD_REQUEST);
        if(userRepository.getUser(post.getUserId()) == null)
            return new ResponseEntity<String>("ID de usuário inexistente.", HttpStatus.BAD_REQUEST);
        if(!userRepository.getUser(post.getUserId()).getSeller())
            return new ResponseEntity<String>("Usuário com o ID "+ post.getUserId() +" não é um vendedor.", HttpStatus.BAD_REQUEST);
        if(productRepository.getProduct(post.getDetail().getProduct_id()) != null){
            return new ResponseEntity<String>("Já existe um Produto com esse ID.", HttpStatus.BAD_REQUEST);
        }

        productRepository.addProduct(post.getDetail());
        postRepository.addPost(dtoToPost(post));

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

        ArrayList<PostDTO> postDTO = new ArrayList<>();

        if(user == null){
            return new ResponseEntity<String>("ID de usuário inexistente.", HttpStatus.BAD_REQUEST);
        }
        List<Post> postsList = new ArrayList<>();
        for(Integer id : user.getFollowingList()){
            if(!(postRepository.sellerPosts(id) == null))
            postsList.addAll(postRepository.sellerPosts(id));
        }
        if(!postsList.isEmpty()) {
            postsList = postsList.stream().filter(p -> nowDate.compareTo(p.getDate()) * p.getDate().compareTo(twoWeekAgoDate) > -1).collect(Collectors.toList());

            if (order != null) {
                if (!orderPostList(postsList, order)) {
                    return new ResponseEntity<String>("Valor do parâmetro 'order' inválido. Insira 'name_asc' ou 'name_desc'.", HttpStatus.BAD_REQUEST);
                }
            } else {
                postsList.sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));
            }

            for (Post p : postsList) {
                postDTO.add(postToDTO(p));
            }
        }

        FollowedSellersPostDTO followedSellersPostDTO = new FollowedSellersPostDTO(idUser, postDTO);
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

        if(!(postList == null)) {
            promoPostCounterDTO.setPromoproducts_count((int) postList.stream().filter(p -> p.isHasPromo()).count());
        }

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

        ArrayList<PostDTO> postDTO = new ArrayList<>();

        if(!(postList == null)) {
            postList = (postList.stream().filter(p -> p.isHasPromo()).collect(Collectors.toList()));

            for (Post p : postList) {
                postDTO.add(postToDTO(p));
            }
        }

        sellerPromoPostsDTO.setPosts(postDTO);

        return new ResponseEntity<SellerPromoPostsDTO>(sellerPromoPostsDTO, HttpStatus.OK);
    }


}
