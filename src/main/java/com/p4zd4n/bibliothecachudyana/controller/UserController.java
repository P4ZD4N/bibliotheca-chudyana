package com.p4zd4n.bibliothecachudyana.controller;

import com.p4zd4n.bibliothecachudyana.entity.*;
import com.p4zd4n.bibliothecachudyana.service.UserService;
import com.p4zd4n.bibliothecachudyana.util.PasswordEncoder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{username}")
    public String displayUserDetails(@PathVariable String username, Model model, Authentication authentication) {
        String authenticatedUsername = authentication.getName();
        boolean isAuthenticatedUserUnauthorizedToEnter = userService.isUnauthorizedToEnter(username, authenticatedUsername);

        if (isAuthenticatedUserUnauthorizedToEnter) {
            return "redirect:/user/" + authenticatedUsername;
        }

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

        return "user/user";
    }

    @GetMapping("/user/{username}/change-email")
    public String displayChangeEmailForm(@PathVariable String username, Model model, Authentication authentication) {
        String authenticatedUsername = authentication.getName();
        boolean isAuthenticatedUserUnauthorizedToEnter = userService.isUnauthorizedToEnter(username, authenticatedUsername);

        if (isAuthenticatedUserUnauthorizedToEnter) {
            model.addAttribute("user", userService.findByUsername(authenticatedUsername));
            model.addAttribute("error", "Brak dostępu!");
            return "user/user";
        }

        User user = userService.findByUsername(username);

        model.addAttribute("user", user);

        return "user/change-email";
    }

    @PostMapping("/change-email")
    public String changeEmail(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "user/change-email";
        }

        user.setAuthorities(userService.findByUsername(user.getUsername()).getAuthorities());

        userService.update(user);

        return "redirect:/user/" + user.getUsername();
    }

    @GetMapping("/user/{username}/change-password")
    public String displayChangePasswordForm(@PathVariable String username, Model model, Authentication authentication) {
        String authenticatedUsername = authentication.getName();
        boolean isAuthenticatedUserUnauthorizedToEnter = userService.isUnauthorizedToEnter(username, authenticatedUsername);

        if (isAuthenticatedUserUnauthorizedToEnter) {
            model.addAttribute("user", userService.findByUsername(authenticatedUsername));
            model.addAttribute("error", "Brak dostępu!");
            return "user/user";
        }

        User user = userService.findByUsername(username);

        model.addAttribute("user", user);

        return "user/change-password";
    }

    @PostMapping("/change-password")
    public String changePassword(
            @RequestParam(value = "currentPassword", required = false) String currentPassword,
            @RequestParam("newPassword") String newPassword,
            @ModelAttribute("user") User user,
            Authentication authentication,
            Model model
    ) {
        boolean isEnteredPasswordEqualToCurrent = PasswordEncoder.checkPassword(currentPassword, user.getPassword().substring(8));
        boolean isAuthenticatedUserEmployee = userService.hasUserEmployeeAuthority(userService.findByUsername(authentication.getName()));
        boolean isAuthenticatedUserManager = userService.hasUserManagerAuthority(userService.findByUsername(authentication.getName()));
        boolean isAuthenticatedUserAdmin = userService.hasUserAdminAuthority(userService.findByUsername(authentication.getName()));

        if (isEnteredPasswordEqualToCurrent || isAuthenticatedUserEmployee || isAuthenticatedUserManager || isAuthenticatedUserAdmin) {
            user.setAuthorities(userService.findByUsername(user.getUsername()).getAuthorities());
            user.setPassword("{bcrypt}" + PasswordEncoder.encodePassword(newPassword));

            userService.update(user);

            return "redirect:/user/" + user.getUsername();
        } else {
            model.addAttribute("error", "Aktualne hasło jest niepoprawne!");
            return "user/change-password";
        }
    }
}
