package com.p4zd4n.bibliothecachudyana.controller;

import com.p4zd4n.bibliothecachudyana.dao.UserDAO;
import com.p4zd4n.bibliothecachudyana.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @GetMapping("/user/{username}")
    public String displayUserDetails(@PathVariable String username, Model model) {
        User user = userDAO.findByUsername(username);
        model.addAttribute("user", user);
        return "/user/user";
    }
}
