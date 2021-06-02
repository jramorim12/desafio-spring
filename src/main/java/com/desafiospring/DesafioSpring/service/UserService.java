package com.desafiospring.DesafioSpring.service;

import com.desafiospring.DesafioSpring.dtos.FollowersCounterDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<String> followSeller(int idUser, int idSeller);
    ResponseEntity followersCounter(int idUser);
    ResponseEntity followersList(int idUser);
    ResponseEntity followedList(int idUser);

}
