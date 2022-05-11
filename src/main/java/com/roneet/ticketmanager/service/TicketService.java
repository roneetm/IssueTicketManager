package com.roneet.ticketmanager.service;

import com.roneet.ticketmanager.entity.Tickets;
import com.roneet.ticketmanager.entity.User;
import com.roneet.ticketmanager.dao.TicketInterface;
import com.roneet.ticketmanager.dao.UserInterface;
import com.roneet.ticketmanager.entity.ticketenums.Priority;
import com.roneet.ticketmanager.entity.ticketenums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class TicketService {

    @Autowired
    TicketInterface ticketInterface;

    @Autowired
    UserInterface userInterface;

    //
    // Creating new Ticket
    public Tickets createTicket(Tickets tickets) {
        tickets.setTicketStatus(Status.OPEN); // Setting default ticket status to OPEN
        tickets.setTicketPriority(Priority.LOW); // // Setting default ticket priority to LOW
        return ticketInterface.save(tickets);
    }

    // Getting list of tickets
    public ResponseEntity<List<Tickets>> ticketList() {
        return new ResponseEntity<>(ticketInterface.findAll(), HttpStatus.OK);
    }

    // Assigning a user to an OPEN ticket
    public ResponseEntity<?> assignUser(Long ticketId, List<User> users) {

        Tickets updateTicket = ticketInterface.findById(ticketId).get();

        if (users != null) {
            Set<User> validatedUserList = updateTicket.getAssignedTo();
            for (User user : users) {
                User userObj = userInterface.findById(user.getUserId()).get();
                validatedUserList.add(userObj);
            }
            if (validatedUserList != null) {
                updateTicket.setAssignedTo(validatedUserList);
                ticketInterface.save(updateTicket);
                return ResponseEntity.ok("Users Assigned to ticket " + ticketId + " Successfully");
            }
        }
        return new ResponseEntity<>("Users not assigned to ticket: " + ticketId, HttpStatus.BAD_REQUEST);
    }

    // Removing user from an assigned tickets
    public ResponseEntity<?> removeAssignedUser(Long ticketId, List<User> users) {

        Tickets updateTicket;
        if (ticketInterface.existsById(ticketId)) {
            updateTicket = ticketInterface.findById(ticketId).get();
        } else {
            return new ResponseEntity<>("Ticket with Id " + ticketId + " Not Found", HttpStatus.NOT_FOUND);
        }

        if (users != null) {
            Set<User> validatedUserList = updateTicket.getAssignedTo();
            for (User user : users) {
                User userObj = userInterface.findById(user.getUserId()).get();
                validatedUserList.remove(userObj);
            }
            if (validatedUserList != null) {
                updateTicket.setAssignedTo(validatedUserList);
                ticketInterface.save(updateTicket);
                return ResponseEntity.ok("Users Removed from the ticket " + ticketId + " Successfully");
            }
        }
        return new ResponseEntity<>("Users not removed from the ticket: " + ticketId, HttpStatus.BAD_REQUEST);
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

    // Assigning user to a ticket watchlist
    public ResponseEntity<?> addToWatch(Long ticketId, User user) {

        Tickets tickets;
        if (ticketInterface.existsById(ticketId)) {
            tickets = ticketInterface.findById(ticketId).get();
        } else {
            return new ResponseEntity<>("Ticket with Id " + ticketId + " Not Found", HttpStatus.NOT_FOUND);
        }

        if (userInterface.existsById(user.getUserId())) {
            Set<User> userSet = tickets.getUsersWatching();
            userSet.add(userInterface.findById(user.getUserId()).get());
            tickets.setUsersWatching(userSet);
            ticketInterface.save(tickets);

            return new ResponseEntity<>("User Added to Watch List ", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User Id " + user.getUserId() + " Not Found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> removeFromWatch(Long ticketId, User user) {
        Tickets tickets;
        if (ticketInterface.existsById(ticketId)) {
            tickets = ticketInterface.findById(ticketId).get();
        } else {
            return new ResponseEntity<>("Ticket with Id " + ticketId + " Not Found", HttpStatus.NOT_FOUND);
        }

        if (userInterface.existsById(user.getUserId())) {
            Set<User> userSet = tickets.getUsersWatching();
            userSet.remove(userInterface.findById(user.getUserId()).get());
            tickets.setUsersWatching(userSet);
            ticketInterface.save(tickets);

            return new ResponseEntity<>("User Removed from Watch List ", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User Id " + user.getUserId() + " Not Found", HttpStatus.NOT_FOUND);
        }
    }
}