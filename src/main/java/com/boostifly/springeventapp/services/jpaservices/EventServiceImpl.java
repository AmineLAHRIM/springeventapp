package com.boostifly.springeventapp.services.jpaservices;

import com.boostifly.springeventapp.beans.Event;
import com.boostifly.springeventapp.beans.UserEventDetail;
import com.boostifly.springeventapp.dao.EventDao;
import com.boostifly.springeventapp.dao.UserEventDetailDao;
import com.boostifly.springeventapp.services.EventService;
import com.boostifly.springeventapp.services.UserEventDetailService;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventDao eventDao;

    @Autowired
    private UserEventDetailService userEventDetailService;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Event> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedFilter");
        filter.setParameter("isDeleted", false);
        List<Event> events = this.eventDao.findAll();
        session.disableFilter("deletedFilter");

        return events;
    }

    @Override
    public Event findById(Long id) {
        Optional<Event> getId = this.eventDao.findById(id);
        if (getId.isPresent()) {
            Event currentEvent=getId.get();
            currentEvent.loadDetail=true;
            currentEvent.setUsers(this.userEventDetailService.findAllByEvent_Id(id));
            return currentEvent;
        }
        return null;
    }

    @Override
    public Event save(Event event) {
        Long currentId = event.getId();
        if (currentId != null && currentId != -1) {
            if (this.eventDao.findById(currentId) == null) {
                return this.eventDao.save(event);
            } else {
                return null;
            }
        } else {
            return this.eventDao.save(event);
        }
    }

    @Transactional
    @Override
    public int deleteById(Long id) {
        if (id != null && id != -1) {
            this.eventDao.deleteById(id);
            return 1;
        }
        return 0;
    }

    @Override
    public Event update(Long id, Event event) {
        if (id != -1) {
            Event currentEvent = findById(id);
            if (currentEvent != null) {
                event.setId(currentEvent.getId());
                return this.eventDao.save(event);
            }
            return null;
        }

        return null;

    }
}
