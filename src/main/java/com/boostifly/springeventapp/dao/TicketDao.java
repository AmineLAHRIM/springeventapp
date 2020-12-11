package com.boostifly.springeventapp.dao;

import com.boostifly.springeventapp.beans.Event;
import com.boostifly.springeventapp.beans.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketDao extends JpaRepository<Ticket, Long> {
    List<Ticket> findAllByEvent_Id(Long eventId);
}