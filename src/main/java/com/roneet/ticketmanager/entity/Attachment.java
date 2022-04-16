package com.roneet.ticketmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Attachment {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long attachmentId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "comment_id", referencedColumnName = "commentId")
    private Comment commentId;
}
