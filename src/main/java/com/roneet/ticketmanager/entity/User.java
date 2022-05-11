package com.roneet.ticketmanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String firstName;
    private String lastName;

    @ManyToMany // A user can be working on multiple tickets
    @JsonIgnore
    private Set<Tickets> assignedTickets = new HashSet<>();

    @ManyToMany // A User can watch multiple tickets
    @JsonIgnore
    private Set<Tickets> watchingTickets = new HashSet<>();
}