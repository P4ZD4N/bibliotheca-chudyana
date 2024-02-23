package com.p4zd4n.bibliothecachudyana.controller;


import com.p4zd4n.bibliothecachudyana.dao.BookDAO;
import com.p4zd4n.bibliothecachudyana.entity.Book;
import com.p4zd4n.bibliothecachudyana.search.SearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookDAO bookDAO;

    @GetMapping("/")
    public String showStartPage() {
        return "index";
    }

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
        return "books";
    }

    @GetMapping("/add-book")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "add-book";
    }

    @PostMapping("/add-book")
    public String addBook(@ModelAttribute("book") Book book) {
        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/find-books")
    public String showFindBooksForm(Model model) {
        model.addAttribute("searchForm", new SearchForm());
        return "find-books";
    }

    @PostMapping("/find-books")
    public String findBooks(@ModelAttribute("searchForm") SearchForm searchForm) {
        String searchBy = searchForm.getSearchBy();
        String keyword = searchForm.getKeyword();

        return "redirect:/books?searchBy=" + searchBy + "&keyword=" + keyword;
    }
}
