package com.roneet.ticketmanager.entity;

import com.roneet.ticketmanager.entity.ticketenums.Priority;
import com.roneet.ticketmanager.entity.ticketenums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tickets {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
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

    @OneToMany(mappedBy = "ticketsId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> assignedTo;

    @OneToMany (mappedBy = "ticketsId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments;
}
