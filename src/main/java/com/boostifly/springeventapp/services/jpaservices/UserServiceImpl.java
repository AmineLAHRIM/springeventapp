package com.boostifly.springeventapp.services.jpaservices;

import com.boostifly.springeventapp.beans.User;
import com.boostifly.springeventapp.dao.EventDao;
import com.boostifly.springeventapp.dao.UserDao;
import com.boostifly.springeventapp.services.UserEventDetailService;
import com.boostifly.springeventapp.services.UserService;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserEventDetailService userEventDetailService;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<User> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedFilter");
        filter.setParameter("isDeleted", false);
        List<User> users = this.userDao.findAll();
        session.disableFilter("deletedFilter");

        return users;
    }

    @Override
    public User findById(Long id) {
        Optional<User> getId = this.userDao.findById(id);
        if (getId.isPresent()) {
            User currentUser=getId.get();
            currentUser.loadDetail=true;
            currentUser.setEvents(this.userEventDetailService.findAllByUser_Id(id));
            return currentUser;
        }
        return null;
    }

    @Override
    public User save(User user) {
        if (user.getId() != null && user.getId() != -1) {
            Long currentId=user.getId();
            if (this.userDao.findById(currentId) == null) {
                return this.userDao.save(user);
            } else {
                return null;
            }
        } else {
            return this.userDao.save(user);
        }
    }

    @Transactional
    @Override
    public int deleteById(Long id) {
        if (id != null && id != -1) {
            this.userDao.deleteById(id);
            return 1;
        }
        return 0;
    }

    @Override
    public User update(Long id, User user) {
        if (id != -1) {
            User currentUser = findById(id);
            if (currentUser != null) {
                user.setId(currentUser.getId());
                return this.userDao.save(user);
            }
            return null;
        }

        return null;

    }
}
