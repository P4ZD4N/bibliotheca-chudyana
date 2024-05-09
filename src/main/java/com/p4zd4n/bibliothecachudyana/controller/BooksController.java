package com.p4zd4n.bibliothecachudyana.controller;

import com.p4zd4n.bibliothecachudyana.entity.Book;
import com.p4zd4n.bibliothecachudyana.entity.Review;
import com.p4zd4n.bibliothecachudyana.enums.BookStatus;
import com.p4zd4n.bibliothecachudyana.service.ReviewService;
import com.p4zd4n.bibliothecachudyana.util.FindBooksForm;
import com.p4zd4n.bibliothecachudyana.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BooksController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/books")
    public String displayBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String authorName,
            @RequestParam(required = false) String authorLastName,
            @RequestParam(required = false) Integer minReleaseYear,
            @RequestParam(required = false) Integer maxReleaseYear,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Integer minPages,
            @RequestParam(required = false) Integer maxPages,
            @RequestParam(required = false) BookStatus status,
            Model model
    ) {
        List<List<Book>> searchedBooks = new ArrayList<>();

        if (title != null) {
            searchedBooks.add(bookService.findByTitle(title));
        }

        if (authorName != null) {
            searchedBooks.add(bookService.findByAuthorName(authorName));
        }

        if (authorLastName != null) {
            searchedBooks.add(bookService.findByAuthorLastName(authorLastName));
        }

        if (minReleaseYear != null && maxReleaseYear == null) {
            searchedBooks.add(bookService.findByMinReleaseYear(minReleaseYear));
        }

        if (minReleaseYear == null && maxReleaseYear != null) {
            searchedBooks.add(bookService.findByMaxReleaseYear(maxReleaseYear));
        }

        if (minReleaseYear != null && maxReleaseYear != null) {
            searchedBooks.add(bookService.findByMinAndMaxReleaseYear(maxReleaseYear, maxReleaseYear));
        }

        if (category != null) {
            searchedBooks.add(bookService.findByCategory(category));
        }

        if (minPrice != null && maxPrice == null) {
            searchedBooks.add(bookService.findByMinPrice(minPrice));
        }

        if (minPrice == null && maxPrice != null) {
            searchedBooks.add(bookService.findByMaxPrice(maxPrice));
        }

        if (minPrice != null && maxPrice != null) {
            searchedBooks.add(bookService.findByMinAndMaxPrice(minPrice, maxPrice));
        }

        if (minPages != null && maxPages == null) {
            searchedBooks.add(bookService.findByMinPages(minPages));
        }

        if (minPages == null && maxPages != null) {
            searchedBooks.add(bookService.findByMaxPages(maxPages));
        }

        if (minPages != null && maxPages != null) {
            searchedBooks.add(bookService.findByMinAndMaxPages(minPages, maxPages));
        }

        if (status != null) {
            switch (status) {
                case BookStatus.UNAVAILABLE -> searchedBooks.add(bookService.findUnavailable());
                case BookStatus.LAST_ITEMS -> searchedBooks.add(bookService.findLastItems());
                case BookStatus.AVAILABLE -> searchedBooks.add(bookService.findAvailable());
            }
        }

        if (searchedBooks.isEmpty()) {
            List<Book> allBooks = bookService.findAll();

            model.addAttribute("books", allBooks);
        } else {
            List<Book> commonBooks = new ArrayList<>(searchedBooks.getFirst());

            for (List<Book> books : searchedBooks)
                commonBooks.retainAll(books);

            model.addAttribute("books", commonBooks);
        }

        return "/books/books";
    }

    @GetMapping("/books/{id}")
    public String displayBookDetails(@PathVariable Integer id, Model model) {
        Book book = bookService.findById(id);
        List<Review> reviews = reviewService.findByBook(book);
        BookStatus status = bookService.getStatusOfBookById(id);

        model.addAttribute("book", book);
        model.addAttribute("status", status);
        model.addAttribute("reviews", reviews);

        return "/books/book";
    }

    @GetMapping("/add-book")
    public String displayAddBookForm(Model model) {
        Book book = new Book();
        book.setAddToLibraryDate(LocalDate.now());
        model.addAttribute("book", book);
        return "/books/save-book";
    }

    @PostMapping("/save-book")
    public String saveBook(
            @RequestParam(required = false) Integer id,
            @Valid @ModelAttribute("book") Book book,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "/books/save-book";
        }

        if (id == null)
            bookService.save(book);
        else
            bookService.update(book);

        return "redirect:/books";
    }

    @GetMapping("/find-books")
    public String displayFindBooksForm(Model model) {
        model.addAttribute("findBooksForm", new FindBooksForm());
        return "/books/find-books";
    }

    @PostMapping("/find-books")
    public String findBooks(@ModelAttribute("findBooksForm") FindBooksForm findBooksForm) {
        String title = findBooksForm.getTitle();
        String authorName = findBooksForm.getAuthorName();
        String authorLastName = findBooksForm.getAuthorLastName();
        Integer minReleaseYear = findBooksForm.getMinReleaseYear();
        Integer maxReleaseYear = findBooksForm.getMaxReleaseYear();
        String category = findBooksForm.getCategory();
        Double minPrice = findBooksForm.getMinPrice();
        Double maxPrice = findBooksForm.getMaxPrice();
        Integer minPages = findBooksForm.getMinPages();
        Integer maxPages = findBooksForm.getMaxPages();
        BookStatus status = findBooksForm.getStatus();

        StringBuilder redirectUrlBuilder = new StringBuilder("redirect:/books?");

        if (title != null && !title.isEmpty()) {
            redirectUrlBuilder.append("title=").append(title).append("&");
        }

        if (authorName != null && !authorName.isEmpty()) {
            redirectUrlBuilder.append("authorName=").append(authorName).append("&");
        }

        if (authorLastName != null && !authorLastName.isEmpty()) {
            redirectUrlBuilder.append("authorLastName=").append(authorLastName).append("&");
        }

        if (minReleaseYear != null) {
            redirectUrlBuilder.append("minReleaseYear=").append(minReleaseYear).append("&");
        }

        if (maxReleaseYear != null) {
            redirectUrlBuilder.append("maxReleaseYear=").append(maxReleaseYear).append("&");
        }

        if (category != null && !category.isEmpty()) {
            redirectUrlBuilder.append("category=").append(category).append("&");
        }

        if (minPrice != null) {
            redirectUrlBuilder.append("minPrice=").append(minPrice).append("&");
        }

        if (maxPrice != null) {
            redirectUrlBuilder.append("maxPrice=").append(maxPrice).append("&");
        }

        if (minPages != null) {
            redirectUrlBuilder.append("minPages=").append(minPages).append("&");
        }

        if (maxPages != null) {
            redirectUrlBuilder.append("maxPages=").append(maxPages).append("&");
        }

        if (status != null) {
            redirectUrlBuilder.append("status=").append(status).append("&");
        }

        if (redirectUrlBuilder.charAt(redirectUrlBuilder.length() - 1) == '&') {
            redirectUrlBuilder.deleteCharAt(redirectUrlBuilder.length() - 1);
        }

        return redirectUrlBuilder.toString();
    }

    @GetMapping("/update-book")
    public String displayUpdateBookForm(@RequestParam("bookId") Integer id, Model model) {
        Book book = bookService.findById(id);

        model.addAttribute("book", book);

        return "/books/save-book";
    }

    @GetMapping("/delete-book")
    public String deleteBook(@RequestParam("bookId") Integer id) {
        Book bookToDelete = bookService.findById(id);
        bookService.delete(bookToDelete);
        return "redirect:/books";
    }
}
