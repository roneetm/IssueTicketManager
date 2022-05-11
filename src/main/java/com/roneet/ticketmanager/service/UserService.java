package com.roneet.ticketmanager.service;

import com.roneet.ticketmanager.entity.User;
import com.roneet.ticketmanager.dao.UserInterface;
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

    public User editUser(Long userId, User user) {

      userInterface.findById(userId).map(user1 -> {
         user1.setFirstName(user.getFirstName());
         user1.setLastName(user.getLastName());
          return userInterface.save(user1);
      });
      return null;
    }
}
