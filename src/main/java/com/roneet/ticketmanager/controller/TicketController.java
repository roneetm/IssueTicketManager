package com.roneet.ticketmanager.controller;

import com.roneet.ticketmanager.entity.Tickets;
import com.roneet.ticketmanager.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface TicketController {
    @PostMapping("/create")
    Tickets createTicket(@RequestBody Tickets tickets);

    @GetMapping("")
    ResponseEntity<List<Tickets>> ticketList();

    @GetMapping("/{ticketId}")
    ResponseEntity<?> getSingleTicket(@PathVariable("ticketId") Long ticketId) throws Exception;

    //    @Override
    @PutMapping("/{ticketId}/assign-users")
    ResponseEntity<?> assignUser(@PathVariable("ticketId") Long ticketId, @RequestBody List<User> users);

    @PutMapping("/{ticketId}/edit")
    Tickets editTicket(@PathVariable("ticketId") Long ticketId, @RequestBody Tickets tickets);

    @DeleteMapping("/{ticketId}")
    ResponseEntity<?> deleteTicket(@PathVariable("ticketId") Long ticketId);

    @PutMapping("/{ticketId}/change-status")
    ResponseEntity<?> changeStatus(@PathVariable("ticketId") Long ticketId, @RequestBody Tickets status);

    @PutMapping("/{ticketId}/change-priority")
    ResponseEntity<?> changePriority(@PathVariable("ticketId") Long ticketId, @RequestBody Tickets ticketPriority);

    @PutMapping("{ticketId}/remove-assignee")
    ResponseEntity<?> removeAssignedUser(@PathVariable("ticketId") Long ticketId, @RequestBody List<User> users);

    @PutMapping("{ticketId}/add-to-watch")
    ResponseEntity<?> addToWatch(@PathVariable("ticketId") Long ticketId, @RequestBody User users);

    @PutMapping("{ticketId}/remove-from-watch")
    public ResponseEntity<?> removeFromWatch(@PathVariable("ticketId") Long ticketId, @RequestBody User users);
}
