package com.p4zd4n.bibliothecachudyana.service;

import com.p4zd4n.bibliothecachudyana.entity.Book;
import com.p4zd4n.bibliothecachudyana.entity.Review;

import java.time.LocalDate;
import java.util.List;

public interface ReviewService {
    Review findById(Integer id);
    List<Review> findAll();
    List<Review> findByBook(Book book);
    List<Review> findByUsername(String username);
    List<Review> findByBookId(Integer bookId);
    List<Review> findByRating(Integer rating);
    List<Review> findByMinAddedDate(LocalDate minAddedDate);
    List<Review> findByMaxAddedDate(LocalDate maxAddedDate);
    List<Review> findByMinAndMaxAddedDate(LocalDate minAddedDate, LocalDate maxAddedDate);
    void save(Review review);
    void update(Review review);
    void delete(Review review);
}
