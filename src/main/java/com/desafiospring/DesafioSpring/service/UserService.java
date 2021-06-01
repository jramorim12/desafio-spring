package com.desafiospring.DesafioSpring.service;

import com.desafiospring.DesafioSpring.dtos.FollowersCounterDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<String> followSeller(int idUser, int idSeller);
    ResponseEntity<FollowersCounterDTO> followersCounter(int idUser);
}
