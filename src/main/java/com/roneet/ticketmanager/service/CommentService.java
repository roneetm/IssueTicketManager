package com.roneet.ticketmanager.service;

import com.roneet.ticketmanager.entity.Comment;
import com.roneet.ticketmanager.dao.CommentInterface;
import com.roneet.ticketmanager.dao.TicketInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentInterface commentInterface;

    @Autowired
    TicketInterface ticketInterface;

    public Comment writeComment(Long ticketId, Comment comment) throws Exception {

        Comment comment1 = ticketInterface.findById(ticketId).map(
                tickets -> {
                    comment.setTicketsId(tickets);
                    return commentInterface.save(comment);
                }
        ).orElseThrow(() -> new RuntimeException("No ticket found"));
        return comment;
    }

    public List<Comment> getAllComments() {
        return commentInterface.findAll();
    }

    public Comment updateComment(Long commentId, Comment comment) {
        return comment;
    }
}