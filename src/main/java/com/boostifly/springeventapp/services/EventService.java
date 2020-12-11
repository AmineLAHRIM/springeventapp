package com.boostifly.springeventapp.services;

import com.boostifly.springeventapp.beans.Event;

import java.util.List;

public interface EventService {

    public List<Event> findAll();

    public Event findById(Long id);

    public Event save(Event event);

    public int deleteById(Long id);

    Event update(Long id, Event event);
}
