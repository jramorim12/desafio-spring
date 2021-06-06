package com.desafiospring.DesafioSpring.service;

import com.desafiospring.DesafioSpring.dtos.FollowersCounterDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity followSeller(int idUser, int idSeller);
    ResponseEntity followersCounter(int idUser);
    ResponseEntity followersList(int idUser, String order);
    ResponseEntity followedList(int idUser, String order);
    ResponseEntity unfollowSeller(int idUser, int idSeller);


}
