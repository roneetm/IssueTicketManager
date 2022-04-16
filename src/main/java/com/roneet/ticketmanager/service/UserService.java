package com.roneet.ticketmanager.service;

import com.roneet.ticketmanager.entity.User;
import com.roneet.ticketmanager.repository.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserInterface userInterface;

    public User createUser(User user) {
        return userInterface.save(user);
    }

    public List<User> getAllUsers() {
        return userInterface.findAll();
    }
}
