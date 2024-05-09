package com.p4zd4n.bibliothecachudyana.service.implementation;

import com.p4zd4n.bibliothecachudyana.dao.OrderDAO;
import com.p4zd4n.bibliothecachudyana.dao.UserDAO;
import com.p4zd4n.bibliothecachudyana.entity.Cart;
import com.p4zd4n.bibliothecachudyana.entity.CartItem;
import com.p4zd4n.bibliothecachudyana.entity.Order;
import com.p4zd4n.bibliothecachudyana.entity.User;
import com.p4zd4n.bibliothecachudyana.enums.OrderStatus;
import com.p4zd4n.bibliothecachudyana.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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
    public Order findById(Integer id) {
        return orderDAO.findById(id);
    }

    @Override
    public Order createOrder(String username) {
        Order order = new Order();

        User user = userDAO.findByUsername(username);
        Cart userCart = user.getCart();
        List<CartItem> userCartItems = userCart.getItems();

        Double orderTotalAmount = 0D;
        for (CartItem cartItem : userCartItems) {
            Double itemPrice = cartItem.getBook().getPrice();

            if (cartItem.getBook().getDiscount() == null) {
                orderTotalAmount += itemPrice;
            } else {
                Double discountPercentage = cartItem.getBook().getDiscount().getDiscountPercentage();
                orderTotalAmount += itemPrice - itemPrice * discountPercentage / 100;
            }
        }

        order.setUser(user);
        order.setOrderDate(LocalDate.now());
        order.setTotalAmount(orderTotalAmount);
        order.setStatus(OrderStatus.IN_PROGRESS);

        return order;
    }

    @Override
    public List<Order> findByUsername(String username) {
        return orderDAO.findByUsername(username);
    }

    @Override
    public List<Order> findByMinDate(LocalDate minDate) {
        List<Order> orders = orderDAO.findAll();
        List<Order> ordersAfterMinDate = new ArrayList<>();

        for (Order order : orders)
            if (order.getOrderDate().isAfter(minDate) || order.getOrderDate().isEqual(minDate))
                ordersAfterMinDate.add(order);

        return ordersAfterMinDate;
    }

    @Override
    public List<Order> findByMaxDate(LocalDate maxDate) {
        List<Order> orders = orderDAO.findAll();
        List<Order> ordersBeforeMaxDate = new ArrayList<>();

        for (Order order : orders)
            if(order.getOrderDate().isBefore(maxDate) || order.getOrderDate().isEqual(maxDate))
                ordersBeforeMaxDate.add(order);

        return ordersBeforeMaxDate;
    }

    @Override
    public List<Order> findByMinAndMaxDate(LocalDate minDate, LocalDate maxDate) {
        List<Order> ordersAfterMinDate = findByMinDate(minDate);
        List<Order> ordersBeforeMaxDate = findByMaxDate(maxDate);

        List<Order> ordersAfterMinDateAndBeforeMaxDate = ordersAfterMinDate;
        ordersAfterMinDateAndBeforeMaxDate.retainAll(ordersBeforeMaxDate);

        return ordersAfterMinDateAndBeforeMaxDate;
    }

    @Override
    public List<Order> findByMinTotalAmount(Double minTotalAmount) {
        List<Order> orders = orderDAO.findAll();
        List<Order> ordersWithAppropriateAmount = new ArrayList<>();

        for (Order order : orders)
            if (order.getTotalAmount() >= minTotalAmount)
                ordersWithAppropriateAmount.add(order);

        return ordersWithAppropriateAmount;
    }

    @Override
    public List<Order> findByMaxTotalAmount(Double maxTotalAmount) {
        List<Order> orders = orderDAO.findAll();
        List<Order> ordersWithAppropriateAmount = new ArrayList<>();

        for (Order order : orders)
            if (order.getTotalAmount() <= maxTotalAmount)
                ordersWithAppropriateAmount.add(order);

        return ordersWithAppropriateAmount;
    }

    @Override
    public List<Order> findByMinAndMaxTotalAmount(Double minTotalAmount, Double maxTotalAmount) {
        List<Order> orders = orderDAO.findAll();
        List<Order> ordersWithAppropriateAmount = new ArrayList<>();

        for (Order order : orders)
            if (order.getTotalAmount() >= minTotalAmount && order.getTotalAmount() <= maxTotalAmount)
                ordersWithAppropriateAmount.add(order);

        return ordersWithAppropriateAmount;
    }

    @Override
    public List<Order> findByStatus(OrderStatus status) {
        List<Order> orders = orderDAO.findAll();
        List<Order> ordersWithAppropriateStatus = new ArrayList<>();

        for (Order order : orders)
            if (order.getStatus().equals(status))
                ordersWithAppropriateStatus.add(order);

        return ordersWithAppropriateStatus;
    }

    @Override
    public List<Order> findAll() {
        return orderDAO.findAll();
    }

    @Override
    public void saveOrder(Order order) {
        orderDAO.save(order);
    }

    @Override
    public void update(Order order) {
        orderDAO.update(order);
    }

    @Override
    public void delete(Order order) {
        orderDAO.delete(order);
    }
}
