package com.p4zd4n.bibliothecachudyana.controller;

import com.p4zd4n.bibliothecachudyana.dao.AuthorityDAO;
import com.p4zd4n.bibliothecachudyana.dao.BookDAO;
import com.p4zd4n.bibliothecachudyana.dao.UserDAO;
import com.p4zd4n.bibliothecachudyana.entity.*;
import com.p4zd4n.bibliothecachudyana.service.UserService;
import com.p4zd4n.bibliothecachudyana.util.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class SecurityController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private AuthorityDAO authorityDAO;

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "security/login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "security/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user, Model model) {
        if (userService.isUserAlreadyRegistered(user)) {
            model.addAttribute("error", "Użytkownik o podanej nazwie już istnieje!");
            return "security/register";
        }

        userService.registerUser(user);

        return "redirect:/";
    }
}
