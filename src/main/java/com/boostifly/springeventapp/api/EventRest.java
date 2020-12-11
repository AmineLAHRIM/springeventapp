package com.boostifly.springeventapp.api;

import com.boostifly.springeventapp.beans.Event;
import com.boostifly.springeventapp.beans.Event;
import com.boostifly.springeventapp.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("eventapp/event")
public class EventRest {

    @Autowired
    private EventService eventService;

    @GetMapping("/")
    public List<Event> findAll() {
        return this.eventService.findAll();
    }

    @GetMapping("/{id}")
    public Event findById(@PathVariable Long id) {
        return this.eventService.findById(id);
    }

    @PostMapping("/")
    public Event save(@RequestBody Event event) {
        return this.eventService.save(event);
    }
    @PutMapping("/{id}")
    public Event update(@PathVariable Long id, @RequestBody Event event) {
        return this.eventService.update(id, event);
    }

    @DeleteMapping("/{id}")
    public int deleteById(@PathVariable Long id) {
        return this.eventService.deleteById(id);
    }
}
