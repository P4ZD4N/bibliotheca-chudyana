package com.p4zd4n.bibliothecachudyana.controller;

import com.p4zd4n.bibliothecachudyana.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class TopCategoriesController {

    @Autowired
    private BookService bookService;

    @GetMapping("/top-categories")
    public String showTopCategories(Model model) {
        Map<String, Integer> topCategories = bookService.getTopCategories();

        model.addAttribute("topCategories", topCategories);

        return "/topcategories/top-categories";
    }
}
