package com.p4zd4n.bibliothecachudyana.service;

import com.p4zd4n.bibliothecachudyana.entity.Order;

public interface OrderService {

    void saveOrder(Order order);
    Order createOrder(String username);
}
