package com.roneet.ticketmanager.controller;

import com.roneet.ticketmanager.entity.Tickets;
import com.roneet.ticketmanager.entity.User;
import com.roneet.ticketmanager.entity.ticketenums.Status;
import com.roneet.ticketmanager.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.security.krb5.internal.Ticket;

import java.util.List;

@RestController
@RequestMapping("tickets")
public class TicketControllerImpl {

    Logger logger = LoggerFactory.getLogger(TicketControllerImpl.class);

    @Autowired
    TicketService ticketService;

    @PostMapping("/create")
    public Tickets createTicket(@RequestBody Tickets tickets){
        logger.info("Ticket Created Successfully");
        return ticketService.createTicket(tickets);
    }

    @GetMapping("")
    public ResponseEntity<List<Tickets>> ticketList(){
       return ticketService.ticketList();
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<?> getSingleTicket(@PathVariable ("ticketId") Long ticketId) throws Exception {
        return ResponseEntity.ok(ticketService.getSingleTicket(ticketId));
    }

    @PatchMapping("/{ticketId}/assign-user")
    public ResponseEntity<?> assignUser(@PathVariable("ticketId") Long ticketId, @RequestBody List<User> users ){
        return ticketService.assignUser(ticketId, users);
    }

    @PutMapping("/{ticketId}/edit")
    public Tickets editTicket(@PathVariable("ticketId") Long ticketId, @RequestBody Tickets tickets ){
        return ticketService.editTicket(ticketId, tickets);
    }

    @DeleteMapping("/{ticketId}")
    public ResponseEntity<?> deleteTicket(@PathVariable ("ticketId") Long ticketId){
        return ResponseEntity.ok(ticketService.deleteTicket(ticketId));
    }

    @PutMapping("/{ticketId}/change-status")
    public ResponseEntity<?> changeStatus(@PathVariable ("ticketId") Long ticketId, @RequestBody Tickets status){
        return ResponseEntity.ok(ticketService.changeStatus(ticketId, status));
    }

    @PutMapping("/{ticketId}/change-priority")
    public ResponseEntity<?> changePriority(@PathVariable ("ticketId") Long ticketId, @RequestBody Tickets ticketPriority){
        return ResponseEntity.ok(ticketService.changePriority(ticketId, ticketPriority));
    }
}