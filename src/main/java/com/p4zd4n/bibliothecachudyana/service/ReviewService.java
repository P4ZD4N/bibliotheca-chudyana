package com.p4zd4n.bibliothecachudyana.service;

import com.p4zd4n.bibliothecachudyana.entity.Book;
import com.p4zd4n.bibliothecachudyana.entity.Review;

import java.util.List;

public interface ReviewService {
    List<Review> findAll();
    List<Review> findByBook(Book book);
    void save(Review review);
}
