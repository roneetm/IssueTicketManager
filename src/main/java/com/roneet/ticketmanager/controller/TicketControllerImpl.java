package com.roneet.ticketmanager.controller;

import com.roneet.ticketmanager.entity.Tickets;
import com.roneet.ticketmanager.entity.User;
import com.roneet.ticketmanager.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tickets")
public class TicketControllerImpl implements TicketController {

    Logger logger = LoggerFactory.getLogger(TicketControllerImpl.class);

    @Autowired
    TicketService ticketService;

    @Override
    @PostMapping("/create")
    public Tickets createTicket(@RequestBody Tickets tickets){
        logger.info("Ticket Created Successfully");
        return ticketService.createTicket(tickets);
    }

    @Override
    @GetMapping("")
    public ResponseEntity<List<Tickets>> ticketList(){
       return ticketService.ticketList();
    }

    @Override
    @GetMapping("/{ticketId}")
    public ResponseEntity<?> getSingleTicket(@PathVariable("ticketId") Long ticketId) throws Exception {
        return ResponseEntity.ok(ticketService.getSingleTicket(ticketId));
    }

//    @Override
    @Override
    @PutMapping("/{ticketId}/assign-users")
    public ResponseEntity<?> assignUser(@PathVariable("ticketId") Long ticketId, @RequestBody List<User> users){
        return ticketService.assignUser(ticketId, users);
    }

    @Override
    @PutMapping("/{ticketId}/edit")
    public Tickets editTicket(@PathVariable("ticketId") Long ticketId, @RequestBody Tickets tickets){
        return ticketService.editTicket(ticketId, tickets);
    }

    @Override
    @DeleteMapping("/{ticketId}")
    public ResponseEntity<?> deleteTicket(@PathVariable("ticketId") Long ticketId){
        return ResponseEntity.ok(ticketService.deleteTicket(ticketId));
    }

    @Override
    @PutMapping("/{ticketId}/change-status")
    public ResponseEntity<?> changeStatus(@PathVariable("ticketId") Long ticketId, @RequestBody Tickets status){
        return ResponseEntity.ok(ticketService.changeStatus(ticketId, status));
    }

    @Override
    @PutMapping("/{ticketId}/change-priority")
    public ResponseEntity<?> changePriority(@PathVariable("ticketId") Long ticketId, @RequestBody Tickets ticketPriority){
        return ResponseEntity.ok(ticketService.changePriority(ticketId, ticketPriority));
    }

    @Override
    @PutMapping("{ticketId}/remove-assignee")
    public ResponseEntity<?> removeAssignedUser(@PathVariable("ticketId") Long ticketId, @RequestBody List<User> users){
        return ResponseEntity.ok(ticketService.removeAssignedUser(ticketId, users));
    }

    @Override
    @PutMapping("{ticketId}/add-to-watch")
    public ResponseEntity<?> addToWatch(Long ticketId, User users) {
        return ResponseEntity.ok(ticketService.addToWatch(ticketId, users));
    }

    @Override
    @PutMapping("{ticketId}/remove-from-watch")
    public ResponseEntity<?> removeFromWatch(@PathVariable("ticketId") Long ticketId, @RequestBody User users){
        return ResponseEntity.ok(ticketService.removeFromWatch(ticketId, users));
    }
}