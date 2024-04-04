package com.p4zd4n.bibliothecachudyana.service.implementation;

import com.p4zd4n.bibliothecachudyana.dao.ReviewDAO;
import com.p4zd4n.bibliothecachudyana.entity.Book;
import com.p4zd4n.bibliothecachudyana.entity.Review;
import com.p4zd4n.bibliothecachudyana.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewDAO reviewDAO;

    @Override
    public List<Review> findAll() {
        return reviewDAO.findAll();
    }

    @Override
    public List<Review> findByBook(Book book) {
        return reviewDAO.findByBook(book);
    }

    @Override
    public void save(Review review) {
        reviewDAO.save(review);
    }
}
