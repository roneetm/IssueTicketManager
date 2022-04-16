package com.roneet.ticketmanager.controller;

import com.roneet.ticketmanager.entity.Comment;
import com.roneet.ticketmanager.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{ticketId}/comment/")
public class CommentControllerImpl implements CommentController {

    @Autowired
    CommentService commentService;

    public Comment writeComment(@PathVariable ("ticketId") Long ticketId, @RequestBody Comment comment) throws Exception {
        return commentService.writeComment(ticketId, comment);
    }

        public List<Comment> getAllComments(){
            return commentService.getAllComments();
    }

    public Comment updateComment(@PathVariable ("commentId") Long commentId, @RequestBody Comment comment){
        return commentService.updateComment(commentId, comment);
    }
}
