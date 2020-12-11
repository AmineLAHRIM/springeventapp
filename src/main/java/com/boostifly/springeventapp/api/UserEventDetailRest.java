package com.boostifly.springeventapp.api;

import com.boostifly.springeventapp.beans.Event;
import com.boostifly.springeventapp.beans.User;
import com.boostifly.springeventapp.beans.UserEventDetail;
import com.boostifly.springeventapp.services.UserEventDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("eventapp/userEventDetail")
public class UserEventDetailRest {

    @Autowired
    private UserEventDetailService userEventDetailService;

    @GetMapping("/")
    public List<UserEventDetail> findAll() {
        return this.userEventDetailService.findAll();
    }

    @GetMapping("/{id}")
    public UserEventDetail findById(@PathVariable Long id) {
        return this.userEventDetailService.findById(id);
    }

    @GetMapping("/eventId/{eventId}")
    public List<User> findAllByEvent_Id(@PathVariable Long eventId) {
        return this.userEventDetailService.findAllByEvent_Id(eventId);
    }

    @GetMapping("/userId/{userId}")
    public List<Event> findAllByUser_Id(@PathVariable Long userId) {
        return this.userEventDetailService.findAllByUser_Id(userId);
    }

    @PostMapping("/")
    public UserEventDetail save(@RequestBody UserEventDetail userEventDetail) {
        return this.userEventDetailService.save(userEventDetail);
    }

    @PutMapping("/{id}")
    public UserEventDetail update(@PathVariable Long id, @RequestBody UserEventDetail userEventDetail) {
        return this.userEventDetailService.update(id, userEventDetail);
    }

    @DeleteMapping("/{id}")
    public int deleteById(@PathVariable Long id) {
        return this.userEventDetailService.deleteById(id);
    }

    @DeleteMapping("/userId/{eventId}")
    public int deleteAllByEvent_Id(@PathVariable Long eventId) {
        return this.userEventDetailService.deleteAllByEvent_Id(eventId);
    }

    @DeleteMapping("/userId/{userId}")
    public int deleteAllByUser_Id(@PathVariable Long userId) {
        return this.userEventDetailService.deleteAllByUser_Id(userId);
    }


}
