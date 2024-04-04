package com.p4zd4n.bibliothecachudyana.controller;

import com.p4zd4n.bibliothecachudyana.entity.Authority;
import com.p4zd4n.bibliothecachudyana.entity.Cart;
import com.p4zd4n.bibliothecachudyana.entity.User;
import com.p4zd4n.bibliothecachudyana.entity.Wishlist;
import com.p4zd4n.bibliothecachudyana.service.AuthorityService;
import com.p4zd4n.bibliothecachudyana.service.UserService;
import com.p4zd4n.bibliothecachudyana.util.FindUsersForm;
import com.p4zd4n.bibliothecachudyana.util.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UsersManagementController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityService authorityService;

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
            return "/usersmanagement/users-management";
        }

        if (username != null) {
            model.addAttribute("users", userService.findByUsername(username));
            return "/usersmanagement/users-management";
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

        return "/usersmanagement/users-management";
    }

    @GetMapping("/users-management/add-user")
    public String displayAddUserForm(Model model) {
        User user = new User();

        model.addAttribute("user", user);

        return "/usersmanagement/save-user";
    }

    @PostMapping("/users-management/save-user")
    public String saveUser(
            @RequestParam(required = false) Integer id,
            @RequestParam("authoritiesAsString") List<String> authoritiesAsString,
            @ModelAttribute("user") User user,
            Model model) {

        if (id == null) {
            boolean isUserAlreadyRegistered = userService.isUserAlreadyRegistered(user);
            if (isUserAlreadyRegistered) {
                model.addAttribute("error", "Użytkownik o podanej nazwie już istnieje!");
                return "/admin/save-user";
            } else {
                String hashedPassword = PasswordEncoder.encodePassword(user.getPassword());
                user.setPassword("{bcrypt}" + hashedPassword);

                List<Authority> userAuthorities = new ArrayList<>();
                for (String authority : authoritiesAsString)
                    userAuthorities.add(new Authority(authority, user));
                user.setAuthorities(userAuthorities);

                Wishlist wishlist = new Wishlist(user);
                wishlist.setItems(new ArrayList<>());
                user.setWishlist(wishlist);

                Cart cart = new Cart(user);
                cart.setItems(new ArrayList<>());
                user.setCart(cart);

                userService.save(user);
            }
        } else {
            List<Authority> userAuthorities = new ArrayList<>();

            for (String authority : authoritiesAsString)
                userAuthorities.add(new Authority(authority, user));
            user.setAuthorities(userAuthorities);

            userService.update(user);
        }

        authorityService.cleanUpAuthorities();

        return "redirect:/users-management";
    }

    @GetMapping("/users-management/find-users")
    public String displayFindUsersForm(Model model) {
        model.addAttribute("findUsersForm", new FindUsersForm());
        return "/usersmanagement/find-users";
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

    @GetMapping("/users-management/update-user")
    public String displayUpdateUserForm(@RequestParam("userId") Integer id, Model model) {
        User user = userService.findById(id);
        List<String> userAuthorities = new ArrayList<>();

        for (Authority authority : user.getAuthorities()) {
            userAuthorities.add(authority.getAuthority());
        }

        model.addAttribute("user", user);
        model.addAttribute("userAuthorities", userAuthorities);

        return "/usersmanagement/save-user";
    }

    @GetMapping("/users-management/delete-user")
    public String deleteUser(@RequestParam("userId") Integer id) {
        User user = userService.findById(id);
        userService.delete(user);
        return "usersmanagement:/users-management";
    }
}
