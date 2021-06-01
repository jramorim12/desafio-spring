package com.desafiospring.DesafioSpring.service;

import com.desafiospring.DesafioSpring.dtos.FollowersCounterDTO;
import com.desafiospring.DesafioSpring.entity.User;
import com.desafiospring.DesafioSpring.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private Integer userHasFollowedAlready(User user, int idSeller){
        Integer i = user.getFollowingList().stream().filter(id -> id == idSeller).findFirst().orElse(null);
        return i;
    }

    @Override
    public ResponseEntity<String> followSeller(int idUser, int idSeller) {

        User user = userRepository.getUser(idUser);
        User userSeller = userRepository.getUser(idSeller);

        if(!userSeller.getSeller()){
            return new ResponseEntity<>("O usuário com ID " + idSeller + " não é um vendedor. Usuários podem seguir apenas vendedores.", HttpStatus.BAD_REQUEST);
        }
        if(userSeller == null)
            return new ResponseEntity<>("Nenhum vendendor encontrado com o ID "+ idSeller, HttpStatus.BAD_REQUEST);
        if(user == null)
            return new ResponseEntity<>("Nenhum usuário encontrado com o ID "+ idUser, HttpStatus.BAD_REQUEST);
        if(userHasFollowedAlready(user, idSeller) != null)
            return new ResponseEntity<>("Usuário já segue esse vendedor.", HttpStatus.OK);

        user.getFollowingList().add(idSeller);
        System.out.println(user.getFollowingList());
        userRepository.updateUser(user);
        return new ResponseEntity<>(user.getUserName() + " passou a seguir " + userSeller.getUserName(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity followersCounter(int idUser) {
        User user = userRepository.getUser(idUser);

        if(user == null)
            return new ResponseEntity<String>("Nenhum usuário foi encontrado com esse ID.", HttpStatus.BAD_REQUEST);

        FollowersCounterDTO followersCounterDTO = new FollowersCounterDTO();
        followersCounterDTO.setUserId(user.getUserId());
        followersCounterDTO.setUserName(user.getUserName());
        followersCounterDTO.setFollowers_count(user.getFollowingList().size());

        return new ResponseEntity<FollowersCounterDTO>(followersCounterDTO, HttpStatus.OK);
    }
}
