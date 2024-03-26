package com.p4zd4n.bibliothecachudyana.controller;

import org.springframework.ui.Model;
import com.p4zd4n.bibliothecachudyana.entity.User;
import com.p4zd4n.bibliothecachudyana.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String displayAdminPanel() {
        return "/admin/admin";
    }

    @GetMapping("/users-management")
    public String displayUsersManagementPanel(Model model) {
        List<User> users = userService.findAll();

        model.addAttribute("users", users);

        return "/admin/users-management";
    }
}
