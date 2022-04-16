package com.roneet.ticketmanager.controller;

import com.roneet.ticketmanager.entity.Comment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CommentController {

    @PostMapping("")
    public Comment writeComment(@PathVariable ("ticketId") Long ticketId, @RequestBody Comment comment) throws Exception;

    @GetMapping("")
    public List<Comment> getAllComments();

    @PutMapping("/{commentId}")
    public Comment updateComment(@PathVariable ("commentId") Long commentId, @RequestBody Comment comment);

}