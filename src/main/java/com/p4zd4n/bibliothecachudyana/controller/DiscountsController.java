package com.p4zd4n.bibliothecachudyana.controller;

import com.p4zd4n.bibliothecachudyana.entity.Book;
import com.p4zd4n.bibliothecachudyana.entity.Discount;
import com.p4zd4n.bibliothecachudyana.service.BookService;
import com.p4zd4n.bibliothecachudyana.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DiscountsController {

    @Autowired
    private DiscountService discountService;

    @Autowired
    private BookService bookService;

    @GetMapping("/discounts")
    public String displayDiscounts(Model model) {
        List<Discount> discounts = discountService.findAll();

        model.addAttribute("discounts", discounts);

        return "/discounts/discounts";
    }

    @GetMapping("/add-discount")
    public String displayAddDiscountForm(Model model) {
        List<Book> books = bookService.findAll();
        Discount discount = new Discount();

        model.addAttribute("books", books);
        model.addAttribute("discount", discount);

        return "/discounts/save-discount";
    }

    @PostMapping("/save-discount")
    public String saveDiscount(@ModelAttribute("discount") Discount discount, @RequestParam("bookId") Integer bookId) {
        Book book = bookService.findById(bookId);
        discount.setBook(book);
        discountService.save(discount);
        return "redirect:/discounts";
    }
}
