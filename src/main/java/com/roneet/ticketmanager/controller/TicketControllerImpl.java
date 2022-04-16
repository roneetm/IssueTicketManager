package com.roneet.ticketmanager.controller;

import com.roneet.ticketmanager.entity.Tickets;
import com.roneet.ticketmanager.entity.User;
import com.roneet.ticketmanager.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<Tickets> ticketList(){
       return ticketService.ticketList();
    }

    @PutMapping("/{ticketId}/assign-user")
    public User assignUser(@PathVariable("ticketId") Long ticketId, @RequestBody User users ){
        System.out.println(users.toString());
        return ticketService.assignUser(ticketId, users);
    }

    @PutMapping("/{ticketId}/edit")
    public Tickets editTicket(@PathVariable("ticketId") Long ticketId, @RequestBody Tickets tickets ){
        return ticketService.editTicket(ticketId, tickets);
    }
}