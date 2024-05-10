package com.p4zd4n.bibliothecachudyana.servicetest;

import com.p4zd4n.bibliothecachudyana.dao.ReviewDAO;
import com.p4zd4n.bibliothecachudyana.entity.Book;
import com.p4zd4n.bibliothecachudyana.entity.Review;
import com.p4zd4n.bibliothecachudyana.entity.User;
import com.p4zd4n.bibliothecachudyana.service.implementation.ReviewServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

    @Mock
    private ReviewDAO reviewDAO;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    @Test
    @DisplayName("Test for findById() method")
    public void testFindById() {

        Review review = new Review();
        review.setId(1);

        when(reviewDAO.findById(1)).thenReturn(review);

        Review result = reviewService.findById(1);

        assertEquals(review, result);
    }

    @Test
    @DisplayName("Test for findByBook() method")
    public void testFindByBook() {

        Book book1 = new Book();
        Book book2 = new Book();

        Review review1 = new Review();
        review1.setBook(book1);
        Review review2 = new Review();
        review2.setBook(book1);
        Review review3 = new Review();
        review3.setBook(book2);

        List<Review> book1Reviews = List.of(review1, review2);
        List<Review> book2Reviews = List.of(review3);

        when(reviewDAO.findByBook(book1)).thenReturn(book1Reviews);
        when(reviewDAO.findByBook(book2)).thenReturn(book2Reviews);

        List<Review> result1 = reviewService.findByBook(book1);
        List<Review> result2 = reviewService.findByBook(book2);

        assertIterableEquals(book1Reviews, result1);
        assertIterableEquals(book2Reviews, result2);
    }

    @Test
    @DisplayName("Test for findByUsername() method")
    public void testFindByUsername() {

        User user1 = new User();
        user1.setUsername("testUser");
        User user2 = new User();
        user2.setUsername("user123");

        Review review1 = new Review();
        review1.setUser(user1);
        Review review2 = new Review();
        review2.setUser(user1);
        Review review3 = new Review();
        review3.setUser(user2);

        List<Review> user1Reviews = List.of(review1, review2);
        List<Review> user2Reviews = List.of(review3);

        when(reviewDAO.findByUsername("testUser")).thenReturn(user1Reviews);
        when(reviewDAO.findByUsername("user123")).thenReturn(user2Reviews);

        List<Review> result1 = reviewService.findByUsername("testUser");
        List<Review> result2 = reviewService.findByUsername("user123");

        assertIterableEquals(user1Reviews, result1);
        assertIterableEquals(user2Reviews, result2);
    }

    @Test
    @DisplayName("Test for findByBookId() method")
    public void testFindByBookId() {

        Book book1 = new Book();
        book1.setId(1);
        Book book2 = new Book();
        book2.setId(2);

        Review review1 = new Review();
        review1.setBook(book1);
        Review review2 = new Review();
        review2.setBook(book2);
        Review review3 = new Review();
        review3.setBook(book2);

        List<Review> book1Reviews = List.of(review1);
        List<Review> book2Reviews = List.of(review2, review3);

        when(reviewDAO.findByBookId(1)).thenReturn(book1Reviews);
        when(reviewDAO.findByBookId(2)).thenReturn(book2Reviews);

        List<Review> result1 = reviewService.findByBookId(1);
        List<Review> result2 = reviewService.findByBookId(2);

        assertIterableEquals(book1Reviews, result1);
        assertIterableEquals(book2Reviews, result2);
    }

    @Test
    @DisplayName("Test for findByRating() method")
    public void testFindByRating() {

        Review review1 = new Review();
        review1.setRating(5);
        Review review2 = new Review();
        review2.setRating(4);
        Review review3 = new Review();
        review3.setRating(3);
        Review review4 = new Review();
        review4.setRating(5);

        List<Review> reviewsWith5Rating = List.of(review1, review4);
        List<Review> reviewsWith4Rating = List.of(review2);
        List<Review> reviewsWith3Rating = List.of(review3);

        when(reviewDAO.findByRating(5)).thenReturn(reviewsWith5Rating);
        when(reviewDAO.findByRating(4)).thenReturn(reviewsWith4Rating);
        when(reviewDAO.findByRating(3)).thenReturn(reviewsWith3Rating);

        List<Review> result1 = reviewService.findByRating(5);
        List<Review> result2 = reviewService.findByRating(4);
        List<Review> result3 = reviewService.findByRating(3);

        assertIterableEquals(reviewsWith5Rating, result1);
        assertIterableEquals(reviewsWith4Rating, result2);
        assertIterableEquals(reviewsWith3Rating, result3);
    }

    @Test
    @DisplayName("Test for findByMinAddedDate() method")
    public void testFindByMinAddedDate() {

        Review review1 = new Review();
        review1.setDateAdded(LocalDate.of(2023, 1, 1));
        Review review2 = new Review();
        review2.setDateAdded(LocalDate.of(2022, 1, 1));
        Review review3 = new Review();
        review3.setDateAdded(LocalDate.of(2020, 1, 1));

        List<Review> reviews = List.of(review1, review2, review3);

        when(reviewDAO.findAll()).thenReturn(reviews);

        List<Review> result1 = reviewService.findByMinAddedDate(LocalDate.of(2020, 1, 1));
        List<Review> result2 = reviewService.findByMinAddedDate(LocalDate.of(2022, 5, 10));
        List<Review> result3 = reviewService.findByMinAddedDate(LocalDate.of(2024, 12, 24));

        assertIterableEquals(List.of(review1, review2, review3), result1);
        assertIterableEquals(List.of(review1), result2);
        assertIterableEquals(Collections.emptyList(), result3);
    }

    @Test
    @DisplayName("Test for findByMaxAddedDate() method")
    public void testFindByMaxAddedDate() {

        Review review1 = new Review();
        review1.setDateAdded(LocalDate.of(2023, 1, 1));
        Review review2 = new Review();
        review2.setDateAdded(LocalDate.of(2022, 1, 1));
        Review review3 = new Review();
        review3.setDateAdded(LocalDate.of(2020, 1, 1));

        List<Review> reviews = List.of(review1, review2, review3);

        when(reviewDAO.findAll()).thenReturn(reviews);

        List<Review> result1 = reviewService.findByMaxAddedDate(LocalDate.of(2020, 1, 1));
        List<Review> result2 = reviewService.findByMaxAddedDate(LocalDate.of(2022, 5, 10));
        List<Review> result3 = reviewService.findByMaxAddedDate(LocalDate.of(2024, 12, 24));

        assertIterableEquals(List.of(review3), result1);
        assertIterableEquals(List.of(review2, review3), result2);
        assertIterableEquals(List.of(review1, review2, review3), result3);
    }

    @Test
    @DisplayName("Test for findByMinAndMaxAddedDate() method")
    public void testFindByMinAndMaxAddedDate() {

        Review review1 = new Review();
        review1.setDateAdded(LocalDate.of(2023, 1, 1));
        Review review2 = new Review();
        review2.setDateAdded(LocalDate.of(2022, 1, 1));
        Review review3 = new Review();
        review3.setDateAdded(LocalDate.of(2020, 1, 1));

        List<Review> reviews = List.of(review1, review2, review3);

        when(reviewDAO.findAll()).thenReturn(reviews);

        List<Review> result1 = reviewService.findByMinAndMaxAddedDate(
                LocalDate.of(2020, 1, 1),
                LocalDate.of(2022, 4, 1)
        );
        List<Review> result2 = reviewService.findByMinAndMaxAddedDate(
                LocalDate.of(2022, 5, 10),
                LocalDate.of(2023, 1, 1)
        );
        List<Review> result3 = reviewService.findByMinAndMaxAddedDate(
                LocalDate.of(2024, 12, 24),
                LocalDate.of(2025, 1, 1)
        );

        assertIterableEquals(List.of(review2, review3), result1);
        assertIterableEquals(List.of(review1), result2);
        assertIterableEquals(Collections.emptyList(), result3);
    }

    @Test
    @DisplayName("Test for save() method")
    public void testSave() {

        Review review = new Review();

        reviewService.save(review);

        verify(reviewDAO, times(1)).save(review);
    }

    @Test
    @DisplayName("Test for update() method")
    public void testUpdate() {

        Review review = new Review();

        reviewService.update(review);

        verify(reviewDAO, times(1)).update(review);
    }

    @Test
    @DisplayName("Test for delete() method")
    public void testDelete() {

        Review review = new Review();

        reviewService.delete(review);

        verify(reviewDAO, times(1)).delete(review);
    }
}
