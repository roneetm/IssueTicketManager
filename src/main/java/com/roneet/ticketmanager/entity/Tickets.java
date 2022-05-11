package com.roneet.ticketmanager.entity;

import com.roneet.ticketmanager.entity.ticketenums.Priority;
import com.roneet.ticketmanager.entity.ticketenums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tickets {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long ticketId;

    @NotBlank
    private String ticketName;
    private String ticketDescription;

    @Enumerated (EnumType.STRING)
    private Status ticketStatus;
    @Enumerated (EnumType.STRING)
    private Priority ticketPriority;

    @CreationTimestamp
    private Date createdDate;

    @ManyToMany // A Ticket can be assigned to multiple users
    private Set<User> assignedTo = new HashSet<>();

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "ticket_comments", joinColumns = @JoinColumn(name = "ticketId"),
//            inverseJoinColumns = @JoinColumn(name = "commentId"))
//    private List<Comment> comments;
//
    @ManyToMany
    private Set<User> usersWatching = new HashSet<>();
}
