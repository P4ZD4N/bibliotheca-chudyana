package com.p4zd4n.bibliothecachudyana.controllertest;

import com.p4zd4n.bibliothecachudyana.controller.BooksController;
import com.p4zd4n.bibliothecachudyana.entity.Book;
import com.p4zd4n.bibliothecachudyana.entity.Review;
import com.p4zd4n.bibliothecachudyana.enums.BookStatus;
import com.p4zd4n.bibliothecachudyana.service.BookService;
import com.p4zd4n.bibliothecachudyana.service.ReviewService;
import com.p4zd4n.bibliothecachudyana.util.FindBooksForm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class BooksControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BookService bookService;
    
    @Mock
    private ReviewService reviewService;

    @InjectMocks
    private BooksController booksController;

    @Test
    @DisplayName("Test for displayBooks() method")
    public void testDisplayBooks() {

        List<Book> books = List.of(
                new Book("Pan Tadeusz", "Adam", "Mickiewicz", LocalDate.of(1834, 6, 28), LocalDate.of(2024, 4, 10)),
                new Book("React i TypeScript", "Carl", "Rippon", LocalDate.of(2024, 5, 7), LocalDate.now()),
                new Book("Black Lies", "Joanna", "Balicka", LocalDate.of(2024, 5, 1), LocalDate.of(2024, 3, 1)),
                new Book("Francja. Radość życia", "Cathy", "Yandell", LocalDate.of(2024, 3, 13), LocalDate.of(2023, 12, 31))

        );

        when(bookService.findAll()).thenReturn(books);
        when(bookService.findByTitle("Black Lies")).thenReturn(List.of(books.get(2)));
        when(bookService.findByAuthorName("Carl")).thenReturn(List.of(books.get(1)));
        when(bookService.findByAuthorLastName("Yandell")).thenReturn(List.of(books.get(3)));
        when(bookService.findByMinReleaseYear(2023)).thenReturn(List.of(books.get(1), books.get(2), books.get(3)));
        when(bookService.findByMaxReleaseYear(2023)).thenReturn(List.of(books.get(0)));

        Model model = mock(Model.class);
        String viewName = booksController.displayBooks("Black Lies", null, null, null, null, null, null, null, null, null, null, model);

        verify(model).addAttribute(eq("books"), anyList());

        assertEquals("/books/books", viewName);
    }

    @Test
    @DisplayName("Test for displayBookDetails() method")
    public void testDisplayBookDetails() {

        int id = 1;
        Book mockBook = new Book("React i TypeScript", "Carl", "Rippon", LocalDate.of(2024, 5, 7), LocalDate.now());
        List<Review> mockReviews = List.of(
                new Review(4, "Dobra książka", LocalDate.now()),
                new Review(1, "nudy!!!", LocalDate.of(2024, 5, 5))
        );
        BookStatus mockStatus = BookStatus.AVAILABLE;

        when(bookService.findById(1)).thenReturn(mockBook);
        when(reviewService.findByBook(mockBook)).thenReturn(mockReviews);
        when(bookService.getStatusOfBookById(id)).thenReturn(mockStatus);

        Model model = mock(Model.class);
        String viewName = booksController.displayBookDetails(id, model);

        verify(model).addAttribute("book", mockBook);
        verify(model).addAttribute("reviews", mockReviews);
        verify(model).addAttribute("status", mockStatus);

        assertEquals("/books/book", viewName);
    }

    @Test
    @DisplayName("Test for displayAddBookForm() method")
    public void testDisplayAddBookForm() {

        Model model = mock(Model.class);
        String viewName = booksController.displayAddBookForm(model);

        verify(model).addAttribute(eq("book"), any(Book.class));
        assertEquals("/books/save-book", viewName);
    }

    @Test
    @DisplayName("Test for saveBook() method with valid input")
    public void testSaveBookWithValidInput() {

        Book mockBook = new Book("React i TypeScript", "Carl", "Rippon", LocalDate.of(2024, 5, 7), LocalDate.now());
        BindingResult bindingResult = mock(BindingResult.class);

        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = booksController.saveBook(null, mockBook, bindingResult);

        verify(bookService).save(mockBook);
        assertEquals("redirect:/books", viewName);
    }

    @Test
    @DisplayName("Test for saveBook() method with invalid input")
    public void testSaveBookWithInvalidInput() {

        Book mockBook = new Book();
        BindingResult bindingResult = mock(BindingResult.class);

        when(bindingResult.hasErrors()).thenReturn(true);

        String viewName = booksController.saveBook(null, mockBook, bindingResult);

        verify(bookService, never()).save(mockBook);
        assertEquals("/books/save-book", viewName);
    }

    @Test
    @DisplayName("Test for displayFindBooksForm() method")
    public void testDisplayFindBooksForm() {

        Model model = mock(Model.class);
        String viewName = booksController.displayFindBooksForm(model);

        verify(model).addAttribute(eq("findBooksForm"), any(FindBooksForm.class));
        assertEquals("/books/find-books", viewName);
    }

    @Test
    @DisplayName("Test for findBooks() method")
    public void testFindBooks() {

        FindBooksForm findBooksForm1 = new FindBooksForm();
        findBooksForm1.setTitle("Esencjalista");
        findBooksForm1.setMaxPrice(40.0);

        String redirectUrl1 = booksController.findBooks(findBooksForm1);

        FindBooksForm findBooksForm2 = new FindBooksForm();
        findBooksForm2.setMinPages(200);
        findBooksForm2.setStatus(BookStatus.LAST_ITEMS);

        String redirectUrl2 = booksController.findBooks(findBooksForm2);

        FindBooksForm findBooksForm3 = new FindBooksForm();
        findBooksForm3.setCategory("Historia");
        findBooksForm3.setMinPrice(10.0);
        findBooksForm3.setMaxPrice(25.0);
        findBooksForm3.setMinReleaseYear(2000);

        String redirectUrl3 = booksController.findBooks(findBooksForm3);

        String expectedUrl1 = "redirect:/books?title=Esencjalista&maxPrice=40.0";
        String expectedUrl2 = "redirect:/books?minPages=200&status=LAST_ITEMS";
        String expectedUrl3 = "redirect:/books?minReleaseYear=2000&category=Historia&minPrice=10.0&maxPrice=25.0";

        assertEquals(expectedUrl1, redirectUrl1);
        assertEquals(expectedUrl2, redirectUrl2);
        assertEquals(expectedUrl3, redirectUrl3);
    }

    @Test
    @DisplayName("Test for displayUpdateBookForm() method")
    public void testDisplayUpdateBookForm() {

        int id = 1;
        Book mockBook = new Book("React i TypeScript", "Carl", "Rippon", LocalDate.of(2024, 5, 7), LocalDate.now());

        when(bookService.findById(id)).thenReturn(mockBook);

        Model model = mock(Model.class);
        String viewName = booksController.displayUpdateBookForm(id, model);

        verify(bookService).findById(id);
        verify(model).addAttribute("book", mockBook);
        assertEquals("/books/save-book", viewName);
    }

    @Test
    @DisplayName("Test for deleteBook() method")
    public void testDeleteBook() {

        int id = 1;
        Book mockBook = new Book("React i TypeScript", "Carl", "Rippon", LocalDate.of(2024, 5, 7), LocalDate.now());

        when(bookService.findById(id)).thenReturn(mockBook);

        String viewName = booksController.deleteBook(id);

        verify(bookService).findById(id);
        verify(bookService).delete(mockBook);
        assertEquals("redirect:/books", viewName);
    }

    @Test
    @DisplayName("Test for displayBooks() method access")
    public void testDisplayBooksAccess() throws Exception {

        mockMvc.perform(get("/books").with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("/books/books"));

        mockMvc.perform(get("/books").with(user("manager").roles("MANAGER")))
                .andExpect(status().isOk())
                .andExpect(view().name("/books/books"));

        mockMvc.perform(get("/books").with(user("employee").roles("EMPLOYEE")))
                .andExpect(status().isOk())
                .andExpect(view().name("/books/books"));

        mockMvc.perform(get("/books").with(user("user").roles("USER")))
                .andExpect(status().isOk())
                .andExpect(view().name("/books/books"));

        mockMvc.perform(get("/books").with(anonymous()))
                .andExpect(status().isOk())
                .andExpect(view().name("/books/books"));
    }

    @Test
    @DisplayName("Test for displayBookDetails() method access")
    public void testDisplayBookDetailsAccess() throws Exception {

        mockMvc.perform(get("/books/1").with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("/books/book"));

        mockMvc.perform(get("/books/1").with(user("manager").roles("MANAGER")))
                .andExpect(status().isOk())
                .andExpect(view().name("/books/book"));

        mockMvc.perform(get("/books/1").with(user("employee").roles("EMPLOYEE")))
                .andExpect(status().isOk())
                .andExpect(view().name("/books/book"));

        mockMvc.perform(get("/books/1").with(user("user").roles("USER")))
                .andExpect(status().isOk())
                .andExpect(view().name("/books/book"));

        mockMvc.perform(get("/books/1").with(anonymous()))
                .andExpect(status().isOk())
                .andExpect(view().name("/books/book"));
    }

    @Test
    @DisplayName("Test for displayAddBookForm() method access")
    public void testDisplayAddBookFormAccess() throws Exception {

        mockMvc.perform(get("/add-book").with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("/books/save-book"));

        mockMvc.perform(get("/add-book").with(user("manager").roles("MANAGER")))
                .andExpect(status().isOk())
                .andExpect(view().name("/books/save-book"));

        mockMvc.perform(get("/add-book").with(user("employee").roles("EMPLOYEE")))
                .andExpect(status().isOk())
                .andExpect(view().name("/books/save-book"));

        mockMvc.perform(get("/add-book").with(user("user").roles("USER")))
                .andExpect(status().isForbidden());

        mockMvc.perform(get("/add-book").with(anonymous()))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName("Test for displayFindBooksForm() method access")
    public void testDisplayFindBooksFormAccess() throws Exception {

        mockMvc.perform(get("/find-books").with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("/books/find-books"));
        mockMvc.perform(get("/find-books").with(user("manager").roles("MANAGER")))
                .andExpect(status().isOk())
                .andExpect(view().name("/books/find-books"));
        mockMvc.perform(get("/find-books").with(user("employee").roles("EMPLOYEE")))
                .andExpect(status().isOk())
                .andExpect(view().name("/books/find-books"));
        mockMvc.perform(get("/find-books").with(user("user").roles("USER")))
                .andExpect(status().isOk())
                .andExpect(view().name("/books/find-books"));
        mockMvc.perform(get("/find-books").with(anonymous()))
                .andExpect(status().isOk())
                .andExpect(view().name("/books/find-books"));
    }

    @Test
    @DisplayName("Test for displayUpdateBookForm() method acces")
    public void testDisplayUpdateBookFormAccess() throws Exception {

        int bookId = 1;
        Book mockBook = new Book();

        when(bookService.findById(bookId)).thenReturn(mockBook);

        mockMvc.perform(get("/update-book")
                .param("bookId", String.valueOf(bookId))
                .with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("/books/save-book"));
        mockMvc.perform(get("/update-book")
                .param("bookId", String.valueOf(bookId))
                .with(user("manager").roles("MANAGER")))
                .andExpect(status().isOk())
                .andExpect(view().name("/books/save-book"));
        mockMvc.perform(get("/update-book")
                .param("bookId", String.valueOf(bookId))
                .with(user("employee").roles("EMPLOYEE")))
                .andExpect(status().isOk())
                .andExpect(view().name("/books/save-book"));
        mockMvc.perform(get("/update-book")
                .param("bookId", String.valueOf(bookId))
                .with(user("user").roles("USER")))
                .andExpect(status().isForbidden());
        mockMvc.perform(get("/update-book")
                .param("bookId", String.valueOf(bookId))
                .with(anonymous()))
                .andExpect(status().is3xxRedirection());
    }
}
