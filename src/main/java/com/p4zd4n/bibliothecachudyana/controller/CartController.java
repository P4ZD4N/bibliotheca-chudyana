package com.p4zd4n.bibliothecachudyana.controller;

import com.p4zd4n.bibliothecachudyana.entity.Book;
import com.p4zd4n.bibliothecachudyana.entity.CartItem;
import com.p4zd4n.bibliothecachudyana.entity.User;
import com.p4zd4n.bibliothecachudyana.service.BookService;
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
public class CartController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @GetMapping("/user/{username}/cart")
    public String displayUserCart(@PathVariable String username, Model model, Authentication authentication) {
        String authenticatedUsername = authentication.getName();
        boolean isAuthenticatedUserUnauthorizedToEnter = userService.isUnauthorizedToEnter(username, authenticatedUsername);

        if (isAuthenticatedUserUnauthorizedToEnter) {
            model.addAttribute("user", userService.findByUsername(authenticatedUsername));
            model.addAttribute("error", "Brak dostępu!");
            return "user/user";
        }

        User user = userService.findByUsername(username);
        List<CartItem> userCart = user.getCart().getItems();
        Double cartValue = userService.getCartValue(userCart);

        model.addAttribute("user", user);
        model.addAttribute("userCart", userCart);
        model.addAttribute("cartValue", cartValue);

        return "user/cart";
    }

    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam("bookId") Integer id, Authentication authentication) {
        String username = authentication.getName();

        User user = userService.findByUsername(username);
        Book book = bookService.findById(id);

        userService.addBookToCart(user, book);

        userService.save(user);

        return "redirect:/user/" + username + "/cart";
    }

    @PostMapping("/remove-from-cart")
    public String removeFromCart(@RequestParam("bookId") Integer id, Authentication authentication) {
        String username = authentication.getName();

        User user = userService.findByUsername(username);
        Book book = bookService.findById(id);

        userService.removeBookFromCart(user, book);

        return "redirect:/user/" + username + "/cart";
    }
}
