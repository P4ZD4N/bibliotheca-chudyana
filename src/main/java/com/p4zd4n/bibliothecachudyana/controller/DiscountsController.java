package com.p4zd4n.bibliothecachudyana.controller;

import com.p4zd4n.bibliothecachudyana.entity.Book;
import com.p4zd4n.bibliothecachudyana.entity.Discount;
import com.p4zd4n.bibliothecachudyana.service.BookService;
import com.p4zd4n.bibliothecachudyana.service.DiscountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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

        return "discounts/discounts";
    }

    @GetMapping("/add-discount")
    public String displayAddDiscountForm(Model model) {
        List<Book> books = bookService.findAll();
        Discount discount = new Discount();
        discount.setBook(new Book());

        model.addAttribute("books", books);
        model.addAttribute("discount", discount);

        return "discounts/save-discount";
    }

    @PostMapping("/save-discount")
    public String saveDiscount(
            @RequestParam(required = false) Integer id,
            @RequestParam("bookId") Integer bookId,
            @Valid @ModelAttribute("discount") Discount discount,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            List<Book> books =
                    (id == null) ?
                    List.of(bookService.findById(bookId)) :
                    List.of(discountService.findById(id).getBook());

            model.addAttribute("books", books);
            model.addAttribute("bookId", bookId);

            return "discounts/save-discount";
        }

        Book book = bookService.findById(bookId);
        discount.setBook(book);

        if (id == null) {
            discountService.save(discount);
        } else {
            discountService.update(discount);
        }

        return "redirect:/discounts";
    }

    @GetMapping("/update-discount")
    public String showUpdateDiscountForm(@RequestParam("discountId") Integer id, Model model) {
        Discount discount = discountService.findById(id);

        model.addAttribute("discount", discount);

        return "discounts/save-discount";
    }

    @GetMapping("/delete-discount")
    public String deleteDiscount(@RequestParam("discountId") Integer id) {
        Discount discountToDelete = discountService.findById(id);
        discountService.delete(discountToDelete);
        return "redirect:/discounts";
    }
}
