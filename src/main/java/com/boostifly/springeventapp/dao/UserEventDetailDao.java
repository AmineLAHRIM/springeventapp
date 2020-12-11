package com.boostifly.springeventapp.dao;

import com.boostifly.springeventapp.beans.Event;
import com.boostifly.springeventapp.beans.UserEventDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserEventDetailDao extends JpaRepository<UserEventDetail, Long> {
    List<UserEventDetail> findAllByEvent_Id(Long eventId);

    List<UserEventDetail> findAllByUser_Id(Long userId);

    int deleteAllByEvent_Id(Long eventId);

    int deleteAllByUser_Id(Long userId);
}