package com.p4zd4n.bibliothecachudyana.dao;

import com.p4zd4n.bibliothecachudyana.entity.User;

import java.util.List;

public interface UserDAO {
    User findByUsername(String username);
    List<User> findAll();
    void save(User user);
    void update(User user);
    void delete(User user);
}
