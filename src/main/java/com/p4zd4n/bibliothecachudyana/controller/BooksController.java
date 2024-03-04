package com.p4zd4n.bibliothecachudyana.controller;


import com.p4zd4n.bibliothecachudyana.dao.BookDAO;
import com.p4zd4n.bibliothecachudyana.entity.Book;
import com.p4zd4n.bibliothecachudyana.search.SearchForm;
import com.p4zd4n.bibliothecachudyana.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class BooksController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookDAO bookDAO;

    @GetMapping("/books")
    public String showBooks(
            @RequestParam(required = false) String searchBy,
            @RequestParam(required = false) String keyword,
            Model model
    ) {
        List<Book> books;
        if (searchBy != null && keyword != null) {
            books = switch (searchBy) {
                case "title" -> bookDAO.findByTitle(keyword);
                case "authorName" -> bookDAO.findByAuthorName(keyword);
                case "authorLastName" -> bookDAO.findByAuthorLastName(keyword);
                case "releaseYear" -> bookDAO.findByReleaseYear(keyword);
                case "category" -> bookDAO.findByCategory(keyword);
                default -> throw new IllegalArgumentException();
            };
        } else {
            books = bookDAO.findAll();
        }
        model.addAttribute("books", books);
        return "books/books";
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
            bookDAO.save(book);
        else
            bookDAO.update(book);
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
        Book book = bookDAO.findById(id);

        model.addAttribute("book", book);

        return "books/save-book";
    }

    @GetMapping("/delete-book")
    public String deleteBook(@RequestParam("bookId") Integer id) {
        Book bookToDelete = bookDAO.findById(id);
        bookDAO.delete(bookToDelete);
        return "redirect:/books";
    }
}
