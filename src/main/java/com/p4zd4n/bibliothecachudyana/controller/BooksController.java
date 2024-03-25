package com.p4zd4n.bibliothecachudyana.controller;


import com.p4zd4n.bibliothecachudyana.entity.Book;
import com.p4zd4n.bibliothecachudyana.util.SearchForm;
import com.p4zd4n.bibliothecachudyana.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class BooksController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public String showBooks(
            @RequestParam(required = false) String searchBy,
            @RequestParam(required = false) String keyword,
            Model model
    ) {
        List<Book> books;
        if (searchBy != null && keyword != null) {
            books = switch (searchBy) {
                case "title" -> bookService.findByTitle(keyword);
                case "authorName" -> bookService.findByAuthorName(keyword);
                case "authorLastName" -> bookService.findByAuthorLastName(keyword);
                case "releaseYear" -> bookService.findByReleaseYear(keyword);
                case "category" -> bookService.findByCategory(keyword);
                default -> throw new IllegalArgumentException();
            };
        } else {
            books = bookService.findAll();
        }
        model.addAttribute("books", books);
        return "books/books";
    }

    @GetMapping("/books/{id}")
    public String showBookDetails(@PathVariable Integer id, Model model) {
        Book book = bookService.findById(id);
        String status = bookService.getStatusOfBookById(id);

        model.addAttribute("book", book);
        model.addAttribute("status", status);

        return "/books/book";
    }

    @GetMapping("/add-book")
    public String showAddBookForm(Model model) {
        Book book = new Book();
        book.setAddToLibraryDate(LocalDate.now());
        model.addAttribute("book", book);
        return "books/save-book";
    }

    @PostMapping("/save-book")
    public String saveBook(@RequestParam(required = false) Integer id, @ModelAttribute("book") Book book) {
        if (id == null)
            bookService.save(book);
        else
            bookService.update(book);
        return "redirect:/books";
    }

    @GetMapping("/find-books")
    public String showFindBooksForm(Model model) {
        model.addAttribute("searchForm", new SearchForm());
        return "books/find-books";
    }

    @PostMapping("/find-books")
    public String findBooks(@ModelAttribute("searchForm") SearchForm searchForm) {
        String searchBy = searchForm.getSearchBy();
        String keyword = searchForm.getKeyword();

        return "redirect:/books?searchBy=" + searchBy + "&keyword=" + keyword;
    }

    @GetMapping("/update-book")
    public String showUpdateBookForm(@RequestParam("bookId") Integer id, Model model) {
        Book book = bookService.findById(id);

        model.addAttribute("book", book);

        return "books/save-book";
    }

    @GetMapping("/delete-book")
    public String deleteBook(@RequestParam("bookId") Integer id) {
        Book bookToDelete = bookService.findById(id);
        bookService.delete(bookToDelete);
        return "redirect:/books";
    }
}
