package com.desafiospring.DesafioSpring.service;

import com.desafiospring.DesafioSpring.dtos.FollowedListDTO;
import com.desafiospring.DesafioSpring.dtos.FollowersCounterDTO;
import com.desafiospring.DesafioSpring.dtos.FollowersListDTO;
import com.desafiospring.DesafioSpring.dtos.UserInfoDTO;
import com.desafiospring.DesafioSpring.models.User;
import com.desafiospring.DesafioSpring.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        if(userSeller == null)
            return new ResponseEntity<>("Nenhum vendendor encontrado com o ID "+ idSeller, HttpStatus.BAD_REQUEST);
        if(user == null)
            return new ResponseEntity<>("Nenhum usuário encontrado com o ID "+ idUser, HttpStatus.BAD_REQUEST);
        if(!userSeller.getSeller()){
            return new ResponseEntity<>("O usuário com ID " + idSeller + " não é um vendedor. Usuários podem seguir apenas vendedores.", HttpStatus.BAD_REQUEST);
        }
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
        List<User> usersList = userRepository.getUsers();

        if(user == null)
            return new ResponseEntity<String>("Nenhum usuário foi encontrado com esse ID.", HttpStatus.BAD_REQUEST);
        if(!user.getSeller())
            return new ResponseEntity<String>("Usuário não é um vendedor. Insira o ID de um vendedor.", HttpStatus.BAD_REQUEST);

        FollowersCounterDTO followersCounterDTO = new FollowersCounterDTO();
        followersCounterDTO.setUserId(user.getUserId());
        followersCounterDTO.setUserName(user.getUserName());
        followersCounterDTO.setFollowers_count((int) usersList.stream().filter(u -> u.getFollowingList().contains(idUser)).count());

        return new ResponseEntity<FollowersCounterDTO>(followersCounterDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity followersList(int idUser) {
        User user = userRepository.getUser(idUser);
        List<User> usersList = userRepository.getUsers();

        if(user == null)
            return new ResponseEntity<String>("Nenhum usuário foi encontrado com esse ID.", HttpStatus.BAD_REQUEST);
        if(!user.getSeller())
            return new ResponseEntity<String>("Usuário não é um vendedor. Insira o ID de um vendedor.", HttpStatus.BAD_REQUEST);

        FollowersListDTO followersListDTO = new FollowersListDTO();
        followersListDTO.setUserId(user.getUserId());
        followersListDTO.setUserName(user.getUserName());
        ArrayList<UserInfoDTO> userList = new ArrayList<>();
        List<User> auxList = new ArrayList<>();
        auxList = usersList.stream().filter(u -> u.getFollowingList().contains(idUser)).collect(Collectors.toList());

        for(User u : auxList){
            userList.add(new UserInfoDTO(u.getUserId(), u.getUserName()));
        }

        followersListDTO.setFollowers(userList);

        return new ResponseEntity<FollowersListDTO>(followersListDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity followedList(int idUser) {
        User user = userRepository.getUser(idUser);
        List<User> usersList = userRepository.getUsers();

        if(user == null)
            return new ResponseEntity<String>("Nenhum usuário foi encontrado com esse ID.", HttpStatus.BAD_REQUEST);

        FollowedListDTO followedListDTO = new FollowedListDTO();
        followedListDTO.setUserId(user.getUserId());
        followedListDTO.setUserName(user.getUserName());
        ArrayList<UserInfoDTO> userList = new ArrayList<>();

        for(Integer id : user.getFollowingList()){
            User auxUser = userRepository.getUser(id);
            userList.add(new UserInfoDTO(auxUser.getUserId(), auxUser.getUserName()));
        }

        followedListDTO.setFollowed(userList);

        return new ResponseEntity<FollowedListDTO>(followedListDTO, HttpStatus.OK);
    }
}
