package com.boostifly.springeventapp.services;

import com.boostifly.springeventapp.beans.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    public List<User> findAll();

    public User findById(Long id);

    public User save(User user);

    public int deleteById(Long id);

    User update(Long id, User user);
}
