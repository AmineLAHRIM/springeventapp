package com.boostifly.springeventapp.api;

import com.boostifly.springeventapp.beans.Ticket;
import com.boostifly.springeventapp.beans.User;
import com.boostifly.springeventapp.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("eventapp/ticket")
public class TicketRest {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/")
    public List<Ticket> findAll() {
        return this.ticketService.findAll();
    }

    @GetMapping("/{id}")
    public Ticket findById(@PathVariable Long id) {
        return this.ticketService.findById(id);
    }

    @GetMapping("/eventId/{eventId}")
    public List<Ticket> findAllByEvent_Id(@PathVariable Long eventId) {
        return this.ticketService.findAllByEvent_Id(eventId);
    }

    @PostMapping("/")
    public Ticket save(@RequestBody Ticket ticket) {
        return this.ticketService.save(ticket);
    }

    @PutMapping("/{id}")
    public Ticket update(@PathVariable Long id, @RequestBody Ticket ticket) {
        return this.ticketService.update(id, ticket);
    }

    @DeleteMapping("/{id}")
    public int deleteById(@PathVariable Long id) {
        return this.ticketService.deleteById(id);
    }


}
