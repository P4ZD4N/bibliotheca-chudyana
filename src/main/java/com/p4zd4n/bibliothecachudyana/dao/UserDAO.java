package com.p4zd4n.bibliothecachudyana.dao;

import com.p4zd4n.bibliothecachudyana.entity.User;

import java.util.List;

public interface UserDAO {

    User findById(Integer id);
    User findByUsername(String username);
    User findByEmail(String email);

    List<User> findByAuthorities(List<String> authorities);
    List<User> findByStatus(Integer status);
    List<User> findAll();

    void save(User user);
    void update(User user);
    void delete(User user);
}
