package com.roneet.ticketmanager.controller;

import com.roneet.ticketmanager.entity.User;
import com.roneet.ticketmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping("")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PutMapping("{userId}")
    public User editUser(@RequestBody User user ){
       return userService.editUser(user);
    }
}