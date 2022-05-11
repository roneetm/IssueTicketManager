package com.roneet.ticketmanager.dao;

import com.roneet.ticketmanager.entity.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketInterface extends JpaRepository<Tickets, Long> {

}
