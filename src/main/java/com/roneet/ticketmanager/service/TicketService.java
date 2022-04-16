package com.roneet.ticketmanager.service;

import com.roneet.ticketmanager.entity.Tickets;
import com.roneet.ticketmanager.entity.User;
import com.roneet.ticketmanager.repository.TicketInterface;
import com.roneet.ticketmanager.repository.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    TicketInterface ticketInterface;

    @Autowired
    UserInterface userInterface;


    public Tickets createTicket(Tickets tickets) {
        return ticketInterface.save(tickets);
    }

    public List<Tickets> ticketList() {
        return ticketInterface.findAll();
    }

    public User assignUser(Long ticketId, User users) {

        return ticketInterface.findById(ticketId).map(
                tickets -> {
                    users.setTicketsId(tickets);
                    return userInterface.save(users);
                }
        ).orElseThrow(() -> new RuntimeException("No ticket found"));
    }

    public Tickets editTicket(Long ticketId, Tickets tickets) {

        return ticketInterface.save(tickets);
    }

    public ResponseEntity<String> deleteTicket(Long ticketId) {
        if(ticketInterface.existsById(ticketId)){
            ticketInterface.deleteById(ticketId);
            return ResponseEntity.ok("Deleted Successfully");
        }
        return ResponseEntity.ok("Ticket not found");
    }

    public Optional<Tickets> getSingleTicket(Long ticketId) throws Exception {
        if(ticketInterface.existsById(ticketId)){
          return ticketInterface.findById(ticketId);
        } else{
            throw new Exception("No tickets found");
        }
    }
}