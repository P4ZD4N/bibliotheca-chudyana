package com.p4zd4n.bibliothecachudyana.controller;

import com.p4zd4n.bibliothecachudyana.dao.CartItemDAO;
import com.p4zd4n.bibliothecachudyana.dao.UserDAO;
import com.p4zd4n.bibliothecachudyana.entity.*;
import com.p4zd4n.bibliothecachudyana.service.OrderService;
import com.p4zd4n.bibliothecachudyana.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;


    @GetMapping("/order-form")
    public String displayOrderForm(Model model, Authentication authentication) {
        String username = authentication.getName();

        Order order = orderService.createOrder(username);

        model.addAttribute("order", order);
        model.addAttribute("username", username);

        return "/order/order-form";
    }

    @PostMapping("/order-confirmation")
    public String displayOrderConfirmation(
            @ModelAttribute("order") Order order,
            @RequestParam("username") String username, Model model
    ) {
        User user = userService.findByUsername(username);
        List<CartItem> userCartItems = user.getCart().getItems();

        order.setUser(user);
        order.setItems(new ArrayList<>());

        for (CartItem cartItem : userCartItems) {
            order.getItems().add(new OrderItem(order, cartItem.getBook()));
        }

        model.addAttribute("order", order);
        model.addAttribute("username", username);

        return "/order/order-confirmation";
    }

    @PostMapping("/order")
    public String createOrder(
            @ModelAttribute("order") Order order,
            @RequestParam("username") String username
    ) {
        User user = userService.findByUsername(username);
        List<CartItem> userCartItems = user.getCart().getItems();

        order.setUser(user);
        order.setItems(new ArrayList<>());

        for (CartItem cartItem : userCartItems) {
            order.getItems().add(new OrderItem(order, cartItem.getBook()));
            if (cartItem.getBook().getQuantityInStock() > 0)
                cartItem.getBook().setQuantityInStock(cartItem.getBook().getQuantityInStock() - 1);
        }

        orderService.saveOrder(order);
        userService.removeAllBooksFromCart(user);

        return "redirect:/user/" + username;
    }
}
