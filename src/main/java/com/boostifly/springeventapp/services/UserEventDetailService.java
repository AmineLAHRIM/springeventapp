package com.boostifly.springeventapp.services;

import com.boostifly.springeventapp.beans.Event;
import com.boostifly.springeventapp.beans.User;
import com.boostifly.springeventapp.beans.UserEventDetail;
import com.boostifly.springeventapp.beans.UserEventDetail;

import java.util.List;

public interface UserEventDetailService {

    public List<UserEventDetail> findAll();

    public UserEventDetail findById(Long id);

    public UserEventDetail save(UserEventDetail userEventDetail);

    public int deleteById(Long id);

    public UserEventDetail update(Long id, UserEventDetail userEventDetail);

    public List<User> findAllByEvent_Id(Long eventId);

    public List<Event> findAllByUser_Id(Long userId);

    public int deleteAllByEvent_Id(Long eventId);

    public int deleteAllByUser_Id(Long userId);

}
