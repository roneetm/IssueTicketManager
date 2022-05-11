package com.roneet.ticketmanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;
    private String comment;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Tickets ticketsId;

}
