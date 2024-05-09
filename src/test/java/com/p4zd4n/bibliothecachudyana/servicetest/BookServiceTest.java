package com.p4zd4n.bibliothecachudyana.servicetest;

import com.p4zd4n.bibliothecachudyana.dao.BookDAO;
import com.p4zd4n.bibliothecachudyana.entity.Book;
import com.p4zd4n.bibliothecachudyana.enums.BookStatus;
import com.p4zd4n.bibliothecachudyana.service.implementation.BookServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookDAO bookDAO;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    @DisplayName("Test for findById() method")
    public void testFindById() {

        Book book = new Book();
        book.setId(1);

        when(bookDAO.findById(1)).thenReturn(book);

        Book result = bookService.findById(1);

        assertEquals(book, result);
    }

    @Test
    @DisplayName("Test for findByTitle() method")
    public void testFindByTitle() {


        List<Book> books = new ArrayList<>();
        Book book = new Book();

        book.setTitle("Pan Tadeusz");
        books.add(book);

        when(bookDAO.findByTitle("Pan Tad")).thenReturn(books);
        when(bookDAO.findByTitle("usz")).thenReturn(books);
        when(bookDAO.findByTitle("pan tad")).thenReturn(books);
        when(bookDAO.findByTitle("n t")).thenReturn(books);

        List<Book> result1 = bookService.findByTitle("Pan Tad");
        List<Book> result2 = bookService.findByTitle("usz");
        List<Book> result3 = bookService.findByTitle("pan tad");
        List<Book> result4 = bookService.findByTitle("n t");

        assertIterableEquals(books, result1);
        assertIterableEquals(books, result2);
        assertIterableEquals(books, result3);
        assertIterableEquals(books, result4);
    }

    @Test
    @DisplayName("Test for findByAuthorName() method")
    public void testFindByAuthorName() {

        List<Book> books = new ArrayList<>();
        Book book = new Book();

        book.setAuthorName("Adam");
        books.add(book);

        when(bookDAO.findByAuthorName("A")).thenReturn(books);
        when(bookDAO.findByAuthorName("am")).thenReturn(books);
        when(bookDAO.findByAuthorName("adam")).thenReturn(books);

        List<Book> result1 = bookService.findByAuthorName("A");
        List<Book> result2 = bookService.findByAuthorName("am");
        List<Book> result3 = bookService.findByAuthorName("adam");
        List<Book> result4 = bookService.findByAuthorName("wiktor");

        assertIterableEquals(books, result1);
        assertIterableEquals(books, result2);
        assertIterableEquals(books, result3);
        assertNotEquals(books, result4);
    }

    @Test
    @DisplayName("Test for findByAuthorLastName() method")
    public void testFindByAuthorLastName() {

        List<Book> books = new ArrayList<>();
        Book book = new Book();

        book.setAuthorName("Goggins");
        books.add(book);

        when(bookDAO.findByAuthorLastName("G")).thenReturn(books);
        when(bookDAO.findByAuthorLastName("ins")).thenReturn(books);
        when(bookDAO.findByAuthorLastName("ggi")).thenReturn(books);

        List<Book> result1 = bookService.findByAuthorLastName("G");
        List<Book> result2 = bookService.findByAuthorLastName("ins");
        List<Book> result3 = bookService.findByAuthorLastName("ggi");
        List<Book> result4 = bookService.findByAuthorLastName("mick");

        assertIterableEquals(books, result1);
        assertIterableEquals(books, result2);
        assertIterableEquals(books, result3);
        assertNotEquals(books, result4);
    }

    @Test
    @DisplayName("Test for findByMinReleaseYear() method")
    public void testForFindByMinReleaseYear() {

        List<Book> books = new ArrayList<>();
        Book book = new Book();

        book.setReleaseDate(LocalDate.of(2023, 8, 1));
        books.add(book);

        when(bookDAO.findByMinReleaseYear("2023")).thenReturn(books);
        when(bookDAO.findByMinReleaseYear("2000")).thenReturn(books);

        List<Book> result1 = bookService.findByMinReleaseYear("2023");
        List<Book> result2 = bookService.findByMinReleaseYear("2000");
        List<Book> result3 = bookService.findByMinReleaseYear("2024");

        assertIterableEquals(books, result1);
        assertIterableEquals(books, result2);
        assertNotEquals(books, result3);
    }

    @Test
    @DisplayName("Test for findByMaxReleaseYear() method")
    public void testForFindByMaxReleaseYear() {

        List<Book> books = new ArrayList<>();
        Book book = new Book();

        book.setReleaseDate(LocalDate.of(2000, 8, 1));
        books.add(book);

        when(bookDAO.findByMaxReleaseYear("2023")).thenReturn(books);
        when(bookDAO.findByMaxReleaseYear("2000")).thenReturn(books);

        List<Book> result1 = bookService.findByMaxReleaseYear("2023");
        List<Book> result2 = bookService.findByMaxReleaseYear("2000");
        List<Book> result3 = bookService.findByMaxReleaseYear("1999");

        assertIterableEquals(books, result1);
        assertIterableEquals(books, result2);
        assertNotEquals(books, result3);
    }

    @Test
    @DisplayName("Test for findByMinAndMaxReleaseYear() method")
    public void testForFindByMinAndMaxReleaseYear() {

        List<Book> books = new ArrayList<>();
        Book book = new Book();

        book.setReleaseDate(LocalDate.of(2021, 8, 1));
        books.add(book);

        when(bookDAO.findByMinAndMaxReleaseYear("2020", "2024")).thenReturn(books);
        when(bookDAO.findByMinAndMaxReleaseYear("2000", "2022")).thenReturn(books);

        List<Book> result1 = bookService.findByMinAndMaxReleaseYear("2020", "2024");
        List<Book> result2 = bookService.findByMinAndMaxReleaseYear("2000", "2022");
        List<Book> result3 = bookService.findByMinAndMaxReleaseYear("2005", "2020");

        assertIterableEquals(books, result1);
        assertIterableEquals(books, result2);
        assertNotEquals(books, result3);
    }

    @Test
    @DisplayName("Test for findByCategory() method")
    public void testFindByCategory() {

        List<Book> books = new ArrayList<>();
        Book book = new Book();

        book.setCategory("Rozwój osobisty");
        books.add(book);

        when(bookDAO.findByCategory("roz")).thenReturn(books);
        when(bookDAO.findByCategory("sty")).thenReturn(books);
        when(bookDAO.findByCategory("ój oso")).thenReturn(books);

        List<Book> result1 = bookService.findByCategory("roz");
        List<Book> result2 = bookService.findByCategory("sty");
        List<Book> result3 = bookService.findByCategory("ój oso");
        List<Book> result4 = bookService.findByCategory("historia");
        List<Book> result5 = bookService.findByCategory("sport");

        assertIterableEquals(books, result1);
        assertIterableEquals(books, result2);
        assertIterableEquals(books, result3);
        assertNotEquals(books, result4);
        assertNotEquals(books, result5);
    }

    @Test
    @DisplayName("Test for findByMinPrice() method")
    public void testFindByMinPrice() {

        List<Book> books = new ArrayList<>();
        Book book = new Book();

        book.setPrice(50.0);
        books.add(book);

        when(bookDAO.findByMinPrice(49.0)).thenReturn(books);
        when(bookDAO.findByMinPrice(50.0)).thenReturn(books);

        List<Book> result1 = bookService.findByMinPrice(49.0);
        List<Book> result2 = bookService.findByMinPrice(50.0);
        List<Book> result3 = bookService.findByMinPrice(50.1);

        assertIterableEquals(books, result1);
        assertIterableEquals(books, result2);
        assertNotEquals(books, result3);
    }

    @Test
    @DisplayName("Test for findByMaxPrice() method")
    public void testFindByMaxPrice() {

        List<Book> books = new ArrayList<>();
        Book book = new Book();

        book.setPrice(50.0);
        books.add(book);

        when(bookDAO.findByMaxPrice(50.0)).thenReturn(books);
        when(bookDAO.findByMaxPrice(51.0)).thenReturn(books);

        List<Book> result1 = bookService.findByMaxPrice(50.0);
        List<Book> result2 = bookService.findByMaxPrice(51.0);
        List<Book> result3 = bookService.findByMaxPrice(49.9);

        assertIterableEquals(books, result1);
        assertIterableEquals(books, result2);
        assertNotEquals(books, result3);
    }

    @Test
    @DisplayName("Test for findByMinAndMaxPrice() method")
    public void testFindByMinAndMaxPrice() {

        List<Book> books = new ArrayList<>();
        Book book = new Book();

        book.setPrice(50.0);
        books.add(book);

        when(bookDAO.findByMinAndMaxPrice(49.0, 50.0)).thenReturn(books);
        when(bookDAO.findByMinAndMaxPrice(50.0, 51.0)).thenReturn(books);

        List<Book> result1 = bookService.findByMinAndMaxPrice(49.0, 50.0);
        List<Book> result2 = bookService.findByMinAndMaxPrice(50.0, 51.0);
        List<Book> result3 = bookService.findByMinAndMaxPrice(50.1, 51.0);

        assertIterableEquals(books, result1);
        assertIterableEquals(books, result2);
        assertNotEquals(books, result3);
    }

    @Test
    @DisplayName("Test for findByMinPages() method")
    public void testFindByMinPages() {

        List<Book> books = new ArrayList<>();
        Book book = new Book();

        book.setNumberOfPages(500);
        books.add(book);

        when(bookDAO.findByMinPages(460)).thenReturn(books);
        when(bookDAO.findByMinPages(500)).thenReturn(books);

        List<Book> result1 = bookService.findByMinPages(460);
        List<Book> result2 = bookService.findByMinPages(500);
        List<Book> result3 = bookService.findByMinPages(501);

        assertIterableEquals(books, result1);
        assertIterableEquals(books, result2);
        assertNotEquals(books, result3);
    }

    @Test
    @DisplayName("Test for findByMaxPages() method")
    public void testFindByMaxPages() {

        List<Book> books = new ArrayList<>();
        Book book = new Book();

        book.setNumberOfPages(200);
        books.add(book);

        when(bookDAO.findByMaxPages(200)).thenReturn(books);
        when(bookDAO.findByMaxPages(201)).thenReturn(books);

        List<Book> result1 = bookService.findByMaxPages(200);
        List<Book> result2 = bookService.findByMaxPages(201);
        List<Book> result3 = bookService.findByMaxPages(199);

        assertIterableEquals(books, result1);
        assertIterableEquals(books, result2);
        assertNotEquals(books, result3);
    }

    @Test
    @DisplayName("Test for findByMinAndMaxPages() method")
    public void testFindByMinAndMaxPages() {

        List<Book> books = new ArrayList<>();
        Book book = new Book();

        book.setNumberOfPages(254);
        books.add(book);

        when(bookDAO.findByMinAndMaxPages(220, 260)).thenReturn(books);
        when(bookDAO.findByMinAndMaxPages(254, 255)).thenReturn(books);

        List<Book> result1 = bookService.findByMinAndMaxPages(220, 260);
        List<Book> result2 = bookService.findByMinAndMaxPages(254, 255);
        List<Book> result3 = bookService.findByMinAndMaxPages(255, 256);

        assertIterableEquals(books, result1);
        assertIterableEquals(books, result2);
        assertNotEquals(books, result3);
    }

    @Test
    @DisplayName("Test for findUnavailable() method")
    public void testFindUnavailable() {

        List<Book> books = new ArrayList<>();
        Book book = new Book();

        book.setQuantityInStock(0);
        books.add(book);

        when(bookDAO.findUnavailable()).thenReturn(books);

        List<Book> result1 = bookService.findUnavailable();

        assertIterableEquals(books, result1);
    }

    @Test
    @DisplayName("Test for findLastItems() method")
    public void testFindLastItems() {

        List<Book> books = new ArrayList<>();
        Book book = new Book();

        book.setQuantityInStock(5);
        books.add(book);

        when(bookDAO.findLastItems()).thenReturn(books);

        List<Book> result1 = bookService.findLastItems();

        assertIterableEquals(books, result1);
    }

    @Test
    @DisplayName("Test for findAvailable() method")
    public void testFindAvailable() {

        List<Book> books = new ArrayList<>();
        Book book = new Book();

        book.setQuantityInStock(201);
        books.add(book);

        when(bookDAO.findAvailable()).thenReturn(books);

        List<Book> result1 = bookService.findAvailable();

        assertIterableEquals(books, result1);
    }

    @Test
    @DisplayName("Test for findAll() method")
    public void testFindAll() {

        List<Book> books = new ArrayList<>();
        Book book = new Book();

        books.add(book);

        when(bookDAO.findAll()).thenReturn(books);

        List<Book> result = bookService.findAll();

        assertIterableEquals(books, result);
    }

    @Test
    @DisplayName("Test for getNewReleases() method")
    public void testGetNewReleases() {

        List<Book> allBooks = new ArrayList<>();
        Book book1 = new Book();
        Book book2 = new Book();

        book1.setTitle("Pan Tadeusz");
        book1.setAddToLibraryDate(LocalDate.now());
        book2.setTitle("Esencjalista");
        book2.setAddToLibraryDate(LocalDate.now());

        allBooks.add(book1);
        allBooks.add(book2);

        when(bookDAO.findAll()).thenReturn(allBooks);

        List<Book> newReleases = bookService.getNewReleases();

        assertEquals(2, newReleases.size());
        assertEquals("Pan Tadeusz", newReleases.get(0).getTitle());
        assertEquals("Esencjalista", newReleases.get(1).getTitle());
    }

    @Test
    @DisplayName("Test for getTopCategories() method")
    public void testGetTopCategories() {

        Book book1 = new Book();
        book1.setCategory("Rozwój osobisty");
        Book book2 = new Book();
        book2.setCategory("Informatyka");
        Book book3 = new Book();
        book3.setCategory("Rozwój osobisty");

        List<Book> allBooks = new ArrayList<>(List.of(book1, book2, book3));

        when(bookDAO.findAll()).thenReturn(allBooks);

        Map<String, Integer> topCategories = bookService.getTopCategories();

        assertEquals(2, topCategories.size());
        assertEquals(1, topCategories.get("Informatyka").intValue());
        assertEquals(2, topCategories.get("Rozwój osobisty").intValue());

        List<String> categories = new ArrayList<>(topCategories.keySet());

        assertEquals("Rozwój osobisty", categories.get(0));
        assertEquals("Informatyka", categories.get(1));
    }

    @Test
    @DisplayName("Test for getStatusById() method")
    public void testGetStatusById() {

        Book book1 = new Book();
        Book book2 = new Book();
        Book book3 = new Book();

        book1.setQuantityInStock(0);
        book1.setId(1);
        book2.setQuantityInStock(6);
        book2.setId(2);
        book3.setQuantityInStock(15);
        book3.setId(3);

        when(bookDAO.findById(1)).thenReturn(book1);
        when(bookDAO.findById(2)).thenReturn(book2);
        when(bookDAO.findById(3)).thenReturn(book3);

        BookStatus status1 = bookService.getStatusOfBookById(1);
        BookStatus status2 = bookService.getStatusOfBookById(2);
        BookStatus status3 = bookService.getStatusOfBookById(3);

        assertEquals(BookStatus.UNAVAILABLE, status1);
        assertEquals(BookStatus.LAST_ITEMS, status2);
        assertEquals(BookStatus.AVAILABLE, status3);
    }

    @Test
    @DisplayName("Test for save() method")
    public void testSave() {

        Book book = new Book();

        bookService.save(book);

        verify(bookDAO, times(1)).save(book);
    }

    @Test
    @DisplayName("Test for update() method")
    public void testUpdate() {

        Book book = new Book();

        bookService.update(book);

        verify(bookDAO, times(1)).update(book);
    }

    @Test
    @DisplayName("Test for delete() method")
    public void testDelete() {

        Book book = new Book();

        bookService.delete(book);

        verify(bookDAO, times(1)).delete(book);
    }
}
