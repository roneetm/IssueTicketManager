package com.roneet.ticketmanager.dao;

import com.roneet.ticketmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInterface extends JpaRepository<User, Long> {
}
