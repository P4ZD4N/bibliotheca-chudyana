package com.p4zd4n.bibliothecachudyana.service.implementation;

import com.p4zd4n.bibliothecachudyana.dao.OrderDAO;
import com.p4zd4n.bibliothecachudyana.dao.UserDAO;
import com.p4zd4n.bibliothecachudyana.entity.Cart;
import com.p4zd4n.bibliothecachudyana.entity.CartItem;
import com.p4zd4n.bibliothecachudyana.entity.Order;
import com.p4zd4n.bibliothecachudyana.entity.User;
import com.p4zd4n.bibliothecachudyana.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private UserDAO userDAO;

    private OrderDAO orderDAO;

    @Autowired
    public OrderServiceImpl(OrderDAO orderDAO, UserDAO userDAO) {
        this.orderDAO = orderDAO;
        this.userDAO = userDAO;
    }

    @Override
    public void saveOrder(Order order) {
        orderDAO.save(order);
    }

    @Override
    public Order createOrder(String username) {
        Order order = new Order();

        User user = userDAO.findByUsername(username);
        Cart userCart = user.getCart();
        List<CartItem> userCartItems = userCart.getItems();

        Double orderTotalAmount = 0D;
        for (CartItem cartItem : userCartItems) {
            orderTotalAmount += cartItem.getBook().getPrice();
        }

        order.setUser(user);
        order.setOrderDate(LocalDate.now());
        order.setTotalAmount(orderTotalAmount);
        order.setStatus("IN_PROGRESS");

        return order;
    }
}
