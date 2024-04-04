package com.p4zd4n.bibliothecachudyana.dao;

import com.p4zd4n.bibliothecachudyana.entity.Book;
import com.p4zd4n.bibliothecachudyana.entity.Review;

import java.util.List;

public interface ReviewDAO {
    List<Review> findAll();
    List<Review> findByBook(Book book);
    void save(Review review);
}
