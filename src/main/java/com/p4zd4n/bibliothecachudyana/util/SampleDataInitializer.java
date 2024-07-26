package com.p4zd4n.bibliothecachudyana.util;

import com.p4zd4n.bibliothecachudyana.dao.BookDAO;
import com.p4zd4n.bibliothecachudyana.entity.Authority;
import com.p4zd4n.bibliothecachudyana.entity.Book;
import com.p4zd4n.bibliothecachudyana.entity.Discount;
import com.p4zd4n.bibliothecachudyana.entity.User;
import com.p4zd4n.bibliothecachudyana.service.BookService;
import com.p4zd4n.bibliothecachudyana.service.DiscountService;
import com.p4zd4n.bibliothecachudyana.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class SampleDataInitializer implements CommandLineRunner {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private DiscountService discountService;

    @Override
    public void run(String... args) {

        initSampleUsers();
        initSampleBooks();
    }

    private void initSampleUsers() {

        User sampleUser = new User("user", "user@example.com", "test123");
        userService.registerUser(sampleUser);

        User sampleEmployee = new User("employee", "employee@example.com", "test123");
        sampleEmployee.addAuthority(new Authority("ROLE_EMPLOYEE", sampleEmployee));
        userService.registerUser(sampleEmployee);

        User sampleManager = new User("manager", "manager@example.com", "test123");
        sampleManager.addAuthority(new Authority("ROLE_EMPLOYEE", sampleManager));
        sampleManager.addAuthority(new Authority("ROLE_MANAGER", sampleManager));
        userService.registerUser(sampleManager);

        User sampleAdmin = new User("admin", "admin@example.com", "test123");
        sampleAdmin.addAuthority(new Authority("ROLE_EMPLOYEE", sampleAdmin));
        sampleAdmin.addAuthority(new Authority("ROLE_MANAGER", sampleAdmin));
        sampleAdmin.addAuthority(new Authority("ROLE_ADMIN", sampleAdmin));
        userService.registerUser(sampleAdmin);
    }

    private void initSampleBooks() {


        Book book1 = new Book();
        book1.setTitle("Nic mnie nie złamie");
        book1.setAuthorName("David");
        book1.setAuthorLastName("Goggins");
        book1.setReleaseDate(LocalDate.of(2020, 5, 24));
        book1.setCategory("Rozwój osobisty");
        book1.setAddToLibraryDate(LocalDate.now().minusWeeks(2));
        book1.setNumberOfPages(368);
        book1.setPrice(41.99);
        book1.setQuantityInStock(7);
        bookService.save(book1);

        Discount discountBook1 = new Discount(book1, 20D);
        book1.setDiscount(discountBook1);
        discountService.save(discountBook1);

        Book book2 = new Book();
        book2.setTitle("Informatyk samouk");
        book2.setAuthorName("Cory");
        book2.setAuthorLastName("Althoff");
        book2.setReleaseDate(LocalDate.of(2022, 11, 29));
        book2.setCategory("Informatyka");
        book2.setAddToLibraryDate(LocalDate.now().minusDays(3));
        book2.setNumberOfPages(224);
        book2.setPrice(36.99);
        book2.setQuantityInStock(45);
        bookService.save(book2);

        Book book3 = new Book();
        book3.setTitle("Marka osobista w branży IT. Jak ją zbudować i rozwijać");
        book3.setAuthorName("Krzysztof");
        book3.setAuthorLastName("Kempiński");
        book3.setReleaseDate(LocalDate.of(2024, 2, 20));
        book3.setCategory("Informatyka");
        book3.setAddToLibraryDate(LocalDate.now().minusDays(2));
        book3.setNumberOfPages(176);
        book3.setPrice(32.99);
        book3.setQuantityInStock(66);
        bookService.save(book3);

        Book book4 = new Book();
        book4.setTitle("Pan Tadeusz");
        book4.setAuthorName("Adam");
        book4.setAuthorLastName("Mickiewicz");
        book4.setReleaseDate(LocalDate.of(1834, 6, 1));
        book4.setCategory("Lektury szkolne");
        book4.setAddToLibraryDate(LocalDate.now().minusDays(50));
        book4.setNumberOfPages(448);
        book4.setPrice(29.90);
        book4.setQuantityInStock(200);
        bookService.save(book4);

        Book book5 = new Book();
        book5.setTitle("Harry Potter i Kamień Filozoficzny");
        book5.setAuthorName("J.K.");
        book5.setAuthorLastName("Rowling");
        book5.setReleaseDate(LocalDate.of(1997, 6, 26));
        book5.setCategory("Fantastyka");
        book5.setAddToLibraryDate(LocalDate.now().minusDays(10));
        book5.setNumberOfPages(320);
        book5.setPrice(49.90);
        book5.setQuantityInStock(75);
        bookService.save(book5);

        Discount discountBook5 = new Discount(book5, 80D);
        book5.setDiscount(discountBook5);
        discountService.save(discountBook5);

        Book book6 = new Book();
        book6.setTitle("7 nawyków skutecznego działania");
        book6.setAuthorName("Stephen R.");
        book6.setAuthorLastName("Covey");
        book6.setReleaseDate(LocalDate.of(1989, 1, 1));
        book6.setCategory("Rozwój osobisty");
        book6.setAddToLibraryDate(LocalDate.now().minusDays(2));
        book6.setNumberOfPages(416);
        book6.setPrice(39.90);
        book6.setQuantityInStock(100);
        bookService.save(book6);

        Book book7 = new Book();
        book7.setTitle("Myśl i bogać się");
        book7.setAuthorName("Napoleon");
        book7.setAuthorLastName("Hill");
        book7.setReleaseDate(LocalDate.of(1937, 1, 1));
        book7.setCategory("Rozwój osobisty");
        book7.setAddToLibraryDate(LocalDate.now().minusDays(45));
        book7.setNumberOfPages(256);
        book7.setPrice(34.90);
        book7.setQuantityInStock(0);
        bookService.save(book7);

        Book book8 = new Book();
        book8.setTitle("Jak zdobyć przyjaciół i zjednać sobie ludzi");
        book8.setAuthorName("Dale");
        book8.setAuthorLastName("Carnegie");
        book8.setReleaseDate(LocalDate.of(1936, 1, 1));
        book8.setCategory("Rozwój osobisty");
        book8.setAddToLibraryDate(LocalDate.now().minusDays(20));
        book8.setNumberOfPages(288);
        book8.setPrice(30.00);
        book8.setQuantityInStock(1);
        bookService.save(book8);

        Discount discountBook8 = new Discount(book8, 50D);
        book8.setDiscount(discountBook8);
        discountService.save(discountBook8);
    }
}
