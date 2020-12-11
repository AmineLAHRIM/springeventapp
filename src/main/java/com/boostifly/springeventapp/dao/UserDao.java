package com.boostifly.springeventapp.dao;

import com.boostifly.springeventapp.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

}