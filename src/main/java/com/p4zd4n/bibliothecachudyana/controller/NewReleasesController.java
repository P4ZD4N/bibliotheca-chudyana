package com.p4zd4n.bibliothecachudyana.controller;

import com.p4zd4n.bibliothecachudyana.entity.Book;
import com.p4zd4n.bibliothecachudyana.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class NewReleasesController {

    @Autowired
    private BookService bookService;

    @GetMapping("/new-releases")
    public String displayNewReleases(Model model) {
        List<Book> newBooks = bookService.getNewReleases();
        model.addAttribute("newBooks", newBooks);
        return "/newreleases/new-releases";
    }
}
