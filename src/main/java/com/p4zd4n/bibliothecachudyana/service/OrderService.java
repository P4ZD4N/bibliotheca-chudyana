package com.p4zd4n.bibliothecachudyana.service;

import com.p4zd4n.bibliothecachudyana.entity.Order;
import com.p4zd4n.bibliothecachudyana.enums.OrderStatus;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    Order findById(Integer id);
    List<Order> findByUsername(String username);
    List<Order> findByMinDate(LocalDate minDate);
    List<Order> findByMaxDate(LocalDate maxDate);
    List<Order> findByMinAndMaxDate(LocalDate minDate, LocalDate maxDate);
    List<Order> findByMinTotalAmount(Double minTotalAmount);
    List<Order> findByMaxTotalAmount(Double maxTotalAmount);
    List<Order> findByMinAndMaxTotalAmount(Double minTotalAmount, Double maxTotalAmount);
    List<Order> findByStatus(OrderStatus status);
    List<Order> findAll();
    void saveOrder(Order order);
    void update(Order order);
    void delete(Order order);
    Order createOrder(String username);
}
