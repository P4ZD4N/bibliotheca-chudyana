package com.p4zd4n.bibliothecachudyana.dao;

import com.p4zd4n.bibliothecachudyana.entity.Order;

import java.util.List;

public interface OrderDAO {

    Order findById(Integer id);

    List<Order> findByUsername(String username);
    List<Order> findAll();

    void save(Order order);
    void update(Order order);
    void delete(Order order);
}
