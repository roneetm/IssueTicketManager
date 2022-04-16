package com.roneet.ticketmanager.service;

import com.roneet.ticketmanager.entity.Tickets;
import com.roneet.ticketmanager.entity.User;
import com.roneet.ticketmanager.repository.TicketInterface;
import com.roneet.ticketmanager.repository.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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
}