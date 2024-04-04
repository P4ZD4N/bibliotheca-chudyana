package com.p4zd4n.bibliothecachudyana.dao;

import com.p4zd4n.bibliothecachudyana.entity.Book;
import com.p4zd4n.bibliothecachudyana.entity.Review;

import java.util.List;

public interface ReviewDAO {
    Review findById(Integer id);
    List<Review> findAll();
    List<Review> findByBook(Book book);
    List<Review> findByUsername(String username);
    List<Review> findByBookId(Integer bookId);
    List<Review> findByRating(Integer rating);
    void save(Review review);
    void update(Review review);
    void delete(Review review);
}
