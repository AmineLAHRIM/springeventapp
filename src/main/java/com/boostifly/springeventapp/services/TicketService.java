package com.boostifly.springeventapp.services;

import com.boostifly.springeventapp.beans.Ticket;

import java.util.List;

public interface TicketService {

    public List<Ticket> findAll();

    public Ticket findById(Long id);

    public Ticket save(Ticket ticket);

    public int deleteById(Long id);

    Ticket update(Long id, Ticket ticket);

    public List<Ticket> findAllByEvent_Id(Long eventId);

}
