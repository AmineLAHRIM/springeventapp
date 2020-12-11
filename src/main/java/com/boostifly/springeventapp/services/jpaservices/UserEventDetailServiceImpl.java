package com.boostifly.springeventapp.services.jpaservices;

import com.boostifly.springeventapp.beans.Event;
import com.boostifly.springeventapp.beans.User;
import com.boostifly.springeventapp.beans.UserEventDetail;
import com.boostifly.springeventapp.dao.UserEventDetailDao;
import com.boostifly.springeventapp.services.UserEventDetailService;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserEventDetailServiceImpl implements UserEventDetailService {

    @Autowired
    private UserEventDetailDao userEventDetailDao;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<UserEventDetail> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedFilter");
        filter.setParameter("isDeleted", false);
        List<UserEventDetail> userEventDetails = this.userEventDetailDao.findAll();
        session.disableFilter("deletedFilter");

        return userEventDetails;
    }

    @Override
    public UserEventDetail findById(Long id) {
        Optional<UserEventDetail> getId = this.userEventDetailDao.findById(id);
        if (getId.isPresent()) {
            return getId.get();
        }
        return null;
    }

    @Override
    public UserEventDetail save(UserEventDetail userEventDetail) {
        Long currentId = userEventDetail.getId();
        if (currentId != null && currentId != -1) {
            if (this.userEventDetailDao.findById(currentId) == null) {
                return this.userEventDetailDao.save(userEventDetail);
            } else {
                return null;
            }
        } else {
            return this.userEventDetailDao.save(userEventDetail);
        }
    }

    @Transactional
    @Override
    public int deleteById(Long id) {
        if (id != null && id != -1) {
            this.userEventDetailDao.deleteById(id);
            return 1;
        }
        return 0;
    }

    @Override
    public UserEventDetail update(Long id, UserEventDetail userEventDetail) {
        if (id != -1) {
            UserEventDetail currentUserEventDetail = findById(id);
            if (currentUserEventDetail != null) {
                userEventDetail.setId(currentUserEventDetail.getId());
                return this.userEventDetailDao.save(userEventDetail);
            }
            return null;
        }

        return null;

    }

    @Override
    public List<User> findAllByEvent_Id(Long eventId) {
        List<User> users = new ArrayList<>();
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedFilter");
        filter.setParameter("isDeleted", false);
        List<UserEventDetail> userEventDetails = this.userEventDetailDao.findAllByEvent_Id(eventId);
        session.disableFilter("deletedFilter");
        userEventDetails.forEach(userEventDetail -> {
            users.add(userEventDetail.getUser());
        });
        return users;
    }

    @Override
    public List<Event> findAllByUser_Id(Long userId) {
        List<Event> events = new ArrayList<>();
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedFilter");
        filter.setParameter("isDeleted", false);
        List<UserEventDetail> userEventDetails = this.userEventDetailDao.findAllByUser_Id(userId);
        session.disableFilter("deletedFilter");
        userEventDetails.forEach(userEventDetail -> {
            events.add(userEventDetail.getEvent());
        });

        return events;
    }

    @Override
    public int deleteAllByEvent_Id(Long eventId) {
        return userEventDetailDao.deleteAllByEvent_Id(eventId);
    }

    @Override
    public int deleteAllByUser_Id(Long userId) {
        return userEventDetailDao.deleteAllByUser_Id(userId);

    }
}
