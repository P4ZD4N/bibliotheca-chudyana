package com.p4zd4n.bibliothecachudyana.controller;

import com.p4zd4n.bibliothecachudyana.dao.AuthorityDAO;
import com.p4zd4n.bibliothecachudyana.dao.BookDAO;
import com.p4zd4n.bibliothecachudyana.dao.UserDAO;
import com.p4zd4n.bibliothecachudyana.entity.Authority;
import com.p4zd4n.bibliothecachudyana.entity.Book;
import com.p4zd4n.bibliothecachudyana.entity.User;
import com.p4zd4n.bibliothecachudyana.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private AuthorityDAO authorityDAO;

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private UserService userService;

    @GetMapping("/user/{username}")
    public String displayUserDetails(@PathVariable String username, Model model) {
        User user = userDAO.findByUsername(username);

        List<Authority> authorities = null;
        if (user != null)
            authorities = user.getAuthorities();

        model.addAttribute("user", user);
        if (authorities != null && authorities.size() > 0)
            model.addAttribute("authorities", authorities);

        return "/user/user";
    }

    @GetMapping("/user/{username}/wishlist")
    public String displayUserWishlist(@PathVariable String username, Model model) {
        User user = userDAO.findByUsername(username);
        model.addAttribute("user", user);
        return "/user/wishlist";
    }

    @PostMapping("/add-to-wishlist")
    public String addToWishlist(@RequestParam("bookId") Integer id, Authentication authentication) {
        String username = authentication.getName();

        User user = userDAO.findByUsername(username);
        Book book = bookDAO.findById(id);

        userService.addBookToWishlist(user, book);

        userDAO.save(user);

        return "redirect:/user/" + username;
    }
}
