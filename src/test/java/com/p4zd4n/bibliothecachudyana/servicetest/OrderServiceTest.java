package com.p4zd4n.bibliothecachudyana.servicetest;

import com.p4zd4n.bibliothecachudyana.dao.OrderDAO;
import com.p4zd4n.bibliothecachudyana.dao.UserDAO;
import com.p4zd4n.bibliothecachudyana.entity.*;
import com.p4zd4n.bibliothecachudyana.enums.OrderStatus;
import com.p4zd4n.bibliothecachudyana.service.implementation.OrderServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderDAO orderDAO;

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    @DisplayName("Test for findById() method")
    public void testFindById() {

        Order order = new Order();
        order.setId(1);

        when(orderDAO.findById(1)).thenReturn(order);

        Order result = orderService.findById(1);

        assertEquals(order, result);
    }

    @Test
    @DisplayName("Test for createOrder() method")
    public void testCreateOrder() {

        User user = new User();
        user.setUsername("testUser");

        Cart cart = new Cart();
        List<CartItem> cartItems = new ArrayList<>();

        Book book1 = new Book();
        Book book2 = new Book();

        book1.setPrice(80.0);
        book2.setPrice(54.43);

        CartItem cartItem1 = new CartItem(cart, book1);
        CartItem cartItem2 = new CartItem(cart, book2);

        cartItems.add(cartItem1);
        cartItems.add(cartItem2);
        cart.setItems(cartItems);
        user.setCart(cart);

        when(userDAO.findByUsername("testUser")).thenReturn(user);

        Order order = orderService.createOrder("testUser");

        assertEquals(user, order.getUser());
        assertEquals(LocalDate.now(), order.getOrderDate());
        assertEquals(134.43, order.getTotalAmount());
        assertEquals(OrderStatus.IN_PROGRESS, order.getStatus());
    }

    @Test
    @DisplayName("Test for findByUsername() method")
    public void testFindByUsername() {

        User user = new User();
        List<Order> orders = List.of(new Order(), new Order());

        when(orderDAO.findByUsername(user.getUsername())).thenReturn(orders);

        List<Order> result = orderService.findByUsername(user.getUsername());

        assertIterableEquals(orders, result);
    }

    @Test
    @DisplayName("Test for findByMinDate() method")
    public void testFindByMinDate() {

        Order order1 = new Order();
        order1.setOrderDate(LocalDate.of(2024, 10, 10));
        Order order2 = new Order();
        order2.setOrderDate(LocalDate.of(2023, 1, 15));
        Order order3 = new Order();
        order3.setOrderDate(LocalDate.of(2021, 5, 30));

        List<Order> orders = List.of(order1, order2, order3);

        when(orderDAO.findAll()).thenReturn(orders);

        List<Order> result1 = orderService.findByMinDate(LocalDate.of(2020, 1, 1));
        List<Order> result2 = orderService.findByMinDate(LocalDate.of(2021, 6, 10));
        List<Order> result3 = orderService.findByMinDate(LocalDate.of(2023, 1, 15));

        assertIterableEquals(List.of(order1, order2, order3), result1);
        assertIterableEquals(List.of(order1, order2), result2);
        assertIterableEquals(List.of(order1, order2), result3);
    }

    @Test
    @DisplayName("Test for findByMaxDate() method")
    public void testFindByMaxDate() {

        Order order1 = new Order();
        order1.setOrderDate(LocalDate.of(2024, 10, 10));
        Order order2 = new Order();
        order2.setOrderDate(LocalDate.of(2023, 1, 15));
        Order order3 = new Order();
        order3.setOrderDate(LocalDate.of(2021, 5, 30));

        List<Order> orders = List.of(order1, order2, order3);

        when(orderDAO.findAll()).thenReturn(orders);

        List<Order> result1 = orderService.findByMaxDate(LocalDate.of(2020, 1, 1));
        List<Order> result2 = orderService.findByMaxDate(LocalDate.of(2021, 6, 10));
        List<Order> result3 = orderService.findByMaxDate(LocalDate.of(2023, 1, 15));

        assertIterableEquals(Collections.emptyList(), result1);
        assertIterableEquals(List.of(order3), result2);
        assertIterableEquals(List.of(order2, order3), result3);
    }

    @Test
    @DisplayName("Test for findByMinAndMaxDate() method")
    public void testFindByMinAndMaxDate() {

        Order order1 = new Order();
        order1.setOrderDate(LocalDate.of(2024, 10, 10));
        Order order2 = new Order();
        order2.setOrderDate(LocalDate.of(2023, 1, 15));
        Order order3 = new Order();
        order3.setOrderDate(LocalDate.of(2021, 5, 30));

        List<Order> orders = List.of(order1, order2, order3);

        when(orderDAO.findAll()).thenReturn(orders);


        List<Order> result1 = orderService.findByMinAndMaxDate(
                LocalDate.of(2020, 1, 1),
                LocalDate.of(2021, 6, 1));
        List<Order> result2 = orderService.findByMinAndMaxDate(
                LocalDate.of(2021, 6, 10),
                LocalDate.of(2025, 1, 1));
        List<Order> result3 = orderService.findByMinAndMaxDate(
                LocalDate.of(2023, 1, 15),
                LocalDate.of(2024, 10, 10));

        assertIterableEquals(List.of(order3), result1);
        assertIterableEquals(List.of(order1, order2), result2);
        assertIterableEquals(List.of(order1, order2), result3);
    }

    @Test
    @DisplayName("Test for findByMinAmount() method")
    public void testFindByMinAmount() {

        Order order1 = new Order();
        order1.setTotalAmount(100.0);
        Order order2 = new Order();
        order2.setTotalAmount(55.99);
        Order order3 = new Order();
        order3.setTotalAmount(3.94);

        List<Order> orders = List.of(order1, order2, order3);

        when(orderDAO.findAll()).thenReturn(orders);

        List<Order> result1 = orderService.findByMinTotalAmount(75.0);
        List<Order> result2 = orderService.findByMinTotalAmount(50.0);
        List<Order> result3 = orderService.findByMinTotalAmount(1.0);

        assertIterableEquals(List.of(order1), result1);
        assertIterableEquals(List.of(order1, order2), result2);
        assertIterableEquals(orders, result3);
    }

    @Test
    @DisplayName("Test for findByMaxAmount() method")
    public void testFindByMaxAmount() {

        Order order1 = new Order();
        order1.setTotalAmount(100.0);
        Order order2 = new Order();
        order2.setTotalAmount(55.99);
        Order order3 = new Order();
        order3.setTotalAmount(3.94);

        List<Order> orders = List.of(order1, order2, order3);

        when(orderDAO.findAll()).thenReturn(orders);

        List<Order> result1 = orderService.findByMaxTotalAmount(75.0);
        List<Order> result2 = orderService.findByMaxTotalAmount(50.0);
        List<Order> result3 = orderService.findByMaxTotalAmount(1.0);

        assertIterableEquals(List.of(order2, order3), result1);
        assertIterableEquals(List.of(order3), result2);
        assertIterableEquals(Collections.emptyList(), result3);
    }

    @Test
    @DisplayName("Test for findByMinAndMaxAmount() method")
    public void testFindByMinAndMaxAmount() {

        Order order1 = new Order();
        order1.setTotalAmount(100.0);
        Order order2 = new Order();
        order2.setTotalAmount(55.99);
        Order order3 = new Order();
        order3.setTotalAmount(3.94);

        List<Order> orders = List.of(order1, order2, order3);

        when(orderDAO.findAll()).thenReturn(orders);

        List<Order> result1 = orderService.findByMinAndMaxTotalAmount(75.0, 100.0);
        List<Order> result2 = orderService.findByMinAndMaxTotalAmount(50.0, 99.9);
        List<Order> result3 = orderService.findByMinAndMaxTotalAmount(1.0, 10.0);

        assertIterableEquals(List.of(order1), result1);
        assertIterableEquals(List.of(order2), result2);
        assertIterableEquals(List.of(order3), result3);
    }

    @Test
    @DisplayName("Test for findByStatus() method")
    public void testFindByStatus() {

        Order order1 = new Order();
        order1.setStatus(OrderStatus.IN_PROGRESS);
        Order order2 = new Order();
        order2.setStatus(OrderStatus.RECEIVED);
        Order order3 = new Order();
        order3.setStatus(OrderStatus.SENT);

        List<Order> orders = List.of(order1, order2, order3);

        when(orderDAO.findAll()).thenReturn(orders);

        List<Order> result1 = orderService.findByStatus(OrderStatus.IN_PROGRESS);
        List<Order> result2 = orderService.findByStatus(OrderStatus.RECEIVED);
        List<Order> result3 = orderService.findByStatus(OrderStatus.SENT);

        assertIterableEquals(List.of(order1), result1);
        assertIterableEquals(List.of(order2), result2);
        assertIterableEquals(List.of(order3), result3);
    }

    @Test
    @DisplayName("Test for findAll() method")
    public void testFindAll() {

        Order order1 = new Order();
        Order order2 = new Order();
        Order order3 = new Order();

        List<Order> orders = List.of(order1, order2, order3);

        when(orderDAO.findAll()).thenReturn(orders);

        List<Order> result = orderService.findAll();

        assertIterableEquals(orders, result);
    }

    @Test
    @DisplayName("Test for save() method")
    public void testSave() {

        Order order = new Order();

        orderService.save(order);

        verify(orderDAO, times(1)).save(order);
    }

    @Test
    @DisplayName("Test for update() method")
    public void testUpdate() {

        Order order = new Order();

        orderService.update(order);

        verify(orderDAO, times(1)).update(order);
    }

    @Test
    @DisplayName("Test for delete() method")
    public void testDelete() {

        Order order = new Order();

        orderService.delete(order);

        verify(orderDAO, times(1)).delete(order);
    }
}
