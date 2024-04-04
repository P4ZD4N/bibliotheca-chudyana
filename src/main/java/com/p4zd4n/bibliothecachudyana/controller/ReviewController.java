package com.p4zd4n.bibliothecachudyana.controller;

import com.p4zd4n.bibliothecachudyana.entity.Book;
import com.p4zd4n.bibliothecachudyana.entity.Review;
import com.p4zd4n.bibliothecachudyana.entity.User;
import com.p4zd4n.bibliothecachudyana.service.BookService;
import com.p4zd4n.bibliothecachudyana.service.ReviewService;
import com.p4zd4n.bibliothecachudyana.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.time.LocalDate;

@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @GetMapping("/add-review-for-{bookId}")
    public String displayAddReviewForm(
            @PathVariable Integer bookId,
            Authentication authentication,
            Model model
    ) {
        Review review = new Review();

        model.addAttribute("review", review);
        model.addAttribute("bookId", bookId);
        model.addAttribute("username", authentication.getName());

        return "/reviews/save-review";
    }

    @PostMapping("/save-review")
    public String saveReview(
            @RequestParam("bookId") Integer bookId,
            @RequestParam("username") String username,
            @Valid @ModelAttribute("review") Review review,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("bookId", bookId);
            model.addAttribute("username", username);
            return "/reviews/save-review";
        }

        User user = userService.findByUsername(username);
        Book book = bookService.findById(bookId);

        review.setUser(user);
        review.setBook(book);
        review.setDateAdded(LocalDate.now());

        reviewService.save(review);

        return "redirect:/books/" + bookId;
    }
}
