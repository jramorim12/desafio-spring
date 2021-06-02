package com.desafiospring.DesafioSpring.controller;

import com.desafiospring.DesafioSpring.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExcpetion(Exception e){
        return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/users/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<String> followSeller(@PathVariable int userId, @PathVariable int userIdToFollow){
        if(userId == userIdToFollow)
            return new ResponseEntity("Um usuário não pode se seguir.",HttpStatus.BAD_REQUEST);
        return userService.followSeller(userId, userIdToFollow);
    }

    @GetMapping("/users/{userId}/followers/count/")
    public ResponseEntity numberFollowers(@PathVariable int userId){
        return userService.followersCounter(userId);
    }

    @GetMapping("/users/{userId}/followers/list")
    public ResponseEntity followersList(@PathVariable int userId){
        return userService.followersList(userId);
    }

    @GetMapping("/users/{userId}/followed/list")
    public ResponseEntity followedList(@PathVariable int userId){
        return userService.followedList(userId);
    }

}
