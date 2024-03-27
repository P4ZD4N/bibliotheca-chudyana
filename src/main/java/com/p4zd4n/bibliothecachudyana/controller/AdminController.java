package com.p4zd4n.bibliothecachudyana.controller;

import com.p4zd4n.bibliothecachudyana.util.FindUsersForm;
import org.springframework.ui.Model;
import com.p4zd4n.bibliothecachudyana.entity.User;
import com.p4zd4n.bibliothecachudyana.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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
    public String displayUsersManagementPanel(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) List<String> authorities,
            @RequestParam(required = false) Integer enabled,
            Model model
    ) {

        if (id != null) {
            model.addAttribute("users", userService.findById(id));
            return "/admin/users-management";
        }

        if (username != null) {
            model.addAttribute("users", userService.findByUsername(username));
            return "/admin/users-management";
        }

        List<List<User>> searchedUsers = new ArrayList<>();

        if (authorities != null && !authorities.isEmpty()) {
            searchedUsers.add(userService.findByAuthorities(authorities));
        }

        if (enabled != null) {
            searchedUsers.add(userService.findByStatus(enabled));
        }

        if (searchedUsers.isEmpty()) {
            List<User> users = userService.findAll();
            model.addAttribute("users", users);
        } else {
            List<User> commonUsers = new ArrayList<>(searchedUsers.getFirst());

            for (List<User> users : searchedUsers)
                commonUsers.retainAll(users);

            model.addAttribute("users", commonUsers);
        }

        return "/admin/users-management";
    }

    @GetMapping("/users-management/find-users")
    public String displayFindUsersForm(Model model) {
        model.addAttribute("findUsersForm", new FindUsersForm());
        return "/admin/find-users";
    }

    @PostMapping("/users-management/find-users")
    public String findUsers(@ModelAttribute("findUsersForm") FindUsersForm findUsersForm) {
        Integer id = findUsersForm.getId();
        String username = findUsersForm.getUsername();
        List<String> authorities = findUsersForm.getAuthorities();
        Integer enabled = findUsersForm.getEnabled();

        StringBuilder redirectUrlBuilder = new StringBuilder("redirect:/users-management?");

        if (id != null) {
            redirectUrlBuilder.append("id=").append(id).append("&");
        }

        if (username != null && !username.isEmpty()) {
            redirectUrlBuilder.append("username=").append(username).append("&");
        }

        if (authorities != null && !authorities.isEmpty()) {
            for (String authority : authorities)
                redirectUrlBuilder.append("authorities=").append(authority).append("&");
        }

        if (enabled != null) {
            redirectUrlBuilder.append("enabled=").append(enabled).append("&");
        }

        if (redirectUrlBuilder.charAt(redirectUrlBuilder.length() - 1) == '&') {
            redirectUrlBuilder.deleteCharAt(redirectUrlBuilder.length() - 1);
        }

        return redirectUrlBuilder.toString();
    }
}
