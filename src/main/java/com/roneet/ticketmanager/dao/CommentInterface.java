package com.roneet.ticketmanager.dao;

import com.roneet.ticketmanager.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentInterface extends JpaRepository<Comment, Long> {
}
