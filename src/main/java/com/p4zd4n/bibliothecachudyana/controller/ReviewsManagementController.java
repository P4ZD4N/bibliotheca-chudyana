package com.p4zd4n.bibliothecachudyana.controller;

import com.p4zd4n.bibliothecachudyana.entity.Book;
import com.p4zd4n.bibliothecachudyana.entity.Order;
import com.p4zd4n.bibliothecachudyana.entity.Review;
import com.p4zd4n.bibliothecachudyana.entity.User;
import com.p4zd4n.bibliothecachudyana.service.BookService;
import com.p4zd4n.bibliothecachudyana.service.ReviewService;
import com.p4zd4n.bibliothecachudyana.service.UserService;
import com.p4zd4n.bibliothecachudyana.util.FindOrdersForm;
import com.p4zd4n.bibliothecachudyana.util.FindReviewsForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ReviewsManagementController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @GetMapping("/reviews-management")
    public String displayReviewsManagementPanel(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) Integer bookId,
            @RequestParam(required = false) Integer rating,
            @RequestParam(required = false) LocalDate minAddedDate,
            @RequestParam(required = false) LocalDate maxAddedDate,
            Model model
    ) {
        if (id != null) {
            model.addAttribute("reviews", reviewService.findById(id));
            return "/reviewsmanagement/reviews-management";
        }

        List<List<Review>> searchedReviews = new ArrayList<>();

        if (username != null) {
            searchedReviews.add(reviewService.findByUsername(username));
        }

        if (bookId != null) {
            searchedReviews.add(reviewService.findByBookId(bookId));
        }

        if (rating != null) {
            searchedReviews.add(reviewService.findByRating(rating));
        }

        if (minAddedDate != null && maxAddedDate == null) {
            searchedReviews.add(reviewService.findByMinAddedDate(minAddedDate));
        }

        if (minAddedDate == null && maxAddedDate != null) {
            searchedReviews.add(reviewService.findByMaxAddedDate(maxAddedDate));
        }

        if (minAddedDate != null && maxAddedDate != null) {
            searchedReviews.add(reviewService.findByMinAndMaxAddedDate(minAddedDate, maxAddedDate));
        }

        if (searchedReviews.isEmpty()) {
            List<Review> reviews = reviewService.findAll();
            model.addAttribute("reviews", reviews);
        } else {
            List<Review> commonReviews = new ArrayList<>(searchedReviews.getFirst());

            for (List<Review> reviews : searchedReviews)
                commonReviews.retainAll(reviews);

            model.addAttribute("reviews", commonReviews);
        }

        return "/reviewsmanagement/reviews-management";
    }

    @GetMapping("/reviews-management/find-review")
    public String displayFindReviewsForm(Model model) {
        model.addAttribute("findReviewsForm", new FindReviewsForm());
        model.addAttribute("books", bookService.findAll());
        return "/reviewsmanagement/find-review";
    }

    @PostMapping("/reviews-management/find-review")
    public String findReviews(@ModelAttribute("findReviewsForm") FindReviewsForm findReviewsForm) {
        Integer id = findReviewsForm.getId();
        String username = findReviewsForm.getUsername();
        Integer bookId = findReviewsForm.getBookId();
        Integer rating = findReviewsForm.getRating();
        LocalDate minAddedDate = findReviewsForm.getMinAddedDate();
        LocalDate maxAddedDate = findReviewsForm.getMaxAddedDate();

        StringBuilder redirectUrlBuilder = new StringBuilder("redirect:/reviews-management?");

        if (id != null) {
            redirectUrlBuilder.append("id=").append(id);
            return redirectUrlBuilder.toString();
        }

        if (username != null && !username.isEmpty()) {
            redirectUrlBuilder.append("username=").append(username).append("&");
        }

        if (bookId != null) {
            redirectUrlBuilder.append("bookId=").append(bookId).append("&");
        }

        if (rating != null) {
            redirectUrlBuilder.append("rating=").append(rating).append("&");
        }

        if (minAddedDate != null) {
            redirectUrlBuilder.append("minAddedDate=").append(minAddedDate).append("&");
        }

        if (maxAddedDate != null) {
            redirectUrlBuilder.append("maxAddedDate=").append(maxAddedDate).append("&");
        }

        if (redirectUrlBuilder.charAt(redirectUrlBuilder.length() - 1) == '&') {
            redirectUrlBuilder.deleteCharAt(redirectUrlBuilder.length() - 1);
        }

        return redirectUrlBuilder.toString();
    }

    @GetMapping("/reviews-management/update-review")
    public String displayUpdateReviewForm(@RequestParam("reviewId") Integer id, Model model) {
        Review review = reviewService.findById(id);

        model.addAttribute("bookId", review.getBook().getId());
        model.addAttribute("username", review.getUser().getUsername());
        model.addAttribute("review", review);

        return "/reviewsmanagement/save-review";
    }

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

        return "/reviewsmanagement/save-review";
    }

    @PostMapping("/reviews-management/save-review")
    public String saveReview(
            @RequestParam(required = false) Integer id,
            @RequestParam("bookId") Integer bookId,
            @RequestParam("username") String username,
            @Valid @ModelAttribute("review") Review review,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("bookId", bookId);
            model.addAttribute("username", username);
            return "/reviewsmanagement/save-review";
        }

        User user = userService.findByUsername(username);
        Book book = bookService.findById(bookId);

        review.setUser(user);
        review.setBook(book);
        review.setDateAdded(LocalDate.now());

        if (id == null)
            reviewService.save(review);
        else
            reviewService.update(review);

        return "redirect:/books/" + bookId;
    }

    @GetMapping("/reviews-management/delete-review")
    public String deleteReview(@RequestParam("reviewId") Integer id) {
        Review review = reviewService.findById(id);
        reviewService.delete(review);
        return "redirect:/books/" + review.getBook().getId();
    }
}
