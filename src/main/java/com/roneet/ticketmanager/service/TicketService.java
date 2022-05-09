package com.roneet.ticketmanager.service;

import com.roneet.ticketmanager.entity.Tickets;
import com.roneet.ticketmanager.entity.User;
import com.roneet.ticketmanager.dao.TicketInterface;
import com.roneet.ticketmanager.dao.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    TicketInterface ticketInterface;

    @Autowired
    UserInterface userInterface;

    //Creating new Ticket
    public Tickets createTicket(Tickets tickets) {
        return ticketInterface.save(tickets);
    }

    // Getting list of tickets
    public ResponseEntity<List<Tickets>> ticketList() {
        return new ResponseEntity<>(ticketInterface.findAll(), HttpStatus.OK);
    }

    // Assigning a user to an OPEN ticket
    public ResponseEntity<?> assignUser(Long ticketId, List<User> users) {

        Tickets updateTicket = ticketInterface.findById(ticketId).get();
        List<User> validatedUserList = new ArrayList<>();
        for (User user: users) {
            try{
                if(userInterface.existsById(user.getUserId())){
                    User userObj = userInterface.getById(user.getUserId());
                    userObj.setTicketsId(updateTicket);
                    userInterface.save(userObj);
                }
                else {
                    throw new RuntimeException("User with id "+ user.getUserId() + " does not exist");
                }
//                 validatedUserList.add(userInterface.findById(user.getUserId()).get());
            } catch (RuntimeException runtimeException){
                throw new RuntimeException("User with id "+ user.getUserId() + " does not exist");
            }
        }
//        updateTicket.setAssignedTo(validatedUserList);
//        return ResponseEntity.ok(ticketInterface.save(updateTicket));
        return ResponseEntity.ok("Updated Successfully");
    }

    // Editing a ticket
    public Tickets editTicket(Long ticketId, Tickets tickets) {
        return ticketInterface.save(tickets);
    }

    // Deleting a ticket.
    public ResponseEntity<?> deleteTicket(Long ticketId) {
        if (ticketInterface.existsById(ticketId)) {
            ticketInterface.deleteById(ticketId);
            return ResponseEntity.ok("Deleted Successfully");
        }
        return new ResponseEntity<>("Ticket with  id " + ticketId + " not found", HttpStatus.NOT_FOUND);
    }

    // Fetching a single ticket
    public ResponseEntity<?> getSingleTicket(Long ticketId) throws Exception {
        if (ticketInterface.existsById(ticketId))
            return ResponseEntity.ok(ticketInterface.findById(ticketId));
        return new ResponseEntity<>("No such tickets found", HttpStatus.BAD_REQUEST);
    }

    // Changing status of a ticket.
    public ResponseEntity<?> changeStatus(Long ticketId, Tickets status) {

        if (!ticketInterface.existsById(ticketId))
            return new ResponseEntity<>("Ticket Id Not Found", HttpStatus.NOT_FOUND);

        ticketInterface.findById(ticketId).map(
                tickets -> {
                    tickets.setTicketStatus(status.getTicketStatus());
                    ticketInterface.save(tickets);
                    return ResponseEntity.ok(status);
                }
        );
        return ResponseEntity.ok("Status Changed Successfully");
    }


    // Change Ticket Priority to LOW, MEDIUM or HIGH
    public ResponseEntity<?> changePriority(Long ticketId, Tickets ticketPriority) {

        if (!ticketInterface.existsById(ticketId))
            return new ResponseEntity<>("Ticket Id Not Found", HttpStatus.NOT_FOUND);

        ticketInterface.findById(ticketId).map(
                tickets -> {
                    tickets.setTicketPriority(ticketPriority.getTicketPriority());
                    return ResponseEntity.ok(ticketInterface.save(tickets));
                }
        );
        return ResponseEntity.ok("Priority Changed Successfully");
    }
}