package com.p4zd4n.bibliothecachudyana.controller;

import com.p4zd4n.bibliothecachudyana.dao.AuthorityDAO;
import com.p4zd4n.bibliothecachudyana.dao.UserDAO;
import com.p4zd4n.bibliothecachudyana.entity.Authority;
import com.p4zd4n.bibliothecachudyana.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private AuthorityDAO authorityDAO;

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
}
