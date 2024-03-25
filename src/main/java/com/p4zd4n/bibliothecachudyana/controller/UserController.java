package com.p4zd4n.bibliothecachudyana.controller;

import com.p4zd4n.bibliothecachudyana.entity.*;
import com.p4zd4n.bibliothecachudyana.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{username}")
    public String displayUserDetails(@PathVariable String username, Model model) {
        User user = userService.findByUsername(username);

        List<Authority> authorities = null;
        List<Order> orders = null;

        if (user != null) {
            authorities = user.getAuthorities();
            orders = user.getOrders();
        }

        model.addAttribute("user", user);
        if (authorities != null && !authorities.isEmpty())
            model.addAttribute("authorities", authorities);
        if (orders != null && !orders.isEmpty())
            model.addAttribute("orders", orders);

        return "/user/user";
    }
}
