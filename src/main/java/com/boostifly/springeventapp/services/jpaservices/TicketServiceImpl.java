package com.boostifly.springeventapp.services.jpaservices;

import com.boostifly.springeventapp.beans.Ticket;
import com.boostifly.springeventapp.dao.TicketDao;
import com.boostifly.springeventapp.services.TicketService;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Ticket> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedFilter");
        filter.setParameter("isDeleted", false);
        List<Ticket> tickets = this.ticketDao.findAll();
        session.disableFilter("deletedFilter");

        return tickets;
    }

    @Override
    public Ticket findById(Long id) {
        Optional<Ticket> getId = this.ticketDao.findById(id);
        if (getId.isPresent()) {
            return getId.get();
        }
        return null;
    }

    @Override
    public Ticket save(Ticket ticket) {
        Long currentId = ticket.getId();
        if (currentId != null && currentId != -1) {
            if (this.ticketDao.findById(currentId) == null) {
                return this.ticketDao.save(ticket);
            } else {
                return null;
            }
        } else {
            return this.ticketDao.save(ticket);
        }
    }

    @Transactional
    @Override
    public int deleteById(Long id) {
        if (id != null && id != -1) {
            this.ticketDao.deleteById(id);
            return 1;
        }
        return 0;
    }

    @Override
    public Ticket update(Long id, Ticket ticket) {
        if (id != -1) {
            Ticket currentTicket = findById(id);
            if (currentTicket != null) {
                ticket.setId(currentTicket.getId());
                return this.ticketDao.save(ticket);
            }
            return null;
        }

        return null;

    }

    @Override
    public List<Ticket> findAllByEvent_Id(Long eventId) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedFilter");
        filter.setParameter("isDeleted", false);
        List<Ticket> tickets = this.ticketDao.findAllByEvent_Id(eventId);
        session.disableFilter("deletedFilter");

        return tickets;
    }
}
