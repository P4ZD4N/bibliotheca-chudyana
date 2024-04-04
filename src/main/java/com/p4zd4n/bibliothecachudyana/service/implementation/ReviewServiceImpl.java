package com.p4zd4n.bibliothecachudyana.service.implementation;

import com.p4zd4n.bibliothecachudyana.dao.ReviewDAO;
import com.p4zd4n.bibliothecachudyana.entity.Book;
import com.p4zd4n.bibliothecachudyana.entity.Order;
import com.p4zd4n.bibliothecachudyana.entity.Review;
import com.p4zd4n.bibliothecachudyana.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewDAO reviewDAO;

    @Override
    public Review findById(Integer id) {
        return reviewDAO.findById(id);
    }

    @Override
    public List<Review> findAll() {
        return reviewDAO.findAll();
    }

    @Override
    public List<Review> findByBook(Book book) {
        return reviewDAO.findByBook(book);
    }

    @Override
    public List<Review> findByUsername(String username) {
        return reviewDAO.findByUsername(username);
    }

    @Override
    public List<Review> findByBookId(Integer bookId) {
        return reviewDAO.findByBookId(bookId);
    }

    @Override
    public List<Review> findByRating(Integer rating) {
        return reviewDAO.findByRating(rating);
    }

    @Override
    public List<Review> findByMinAddedDate(LocalDate minAddedDate) {
        List<Review> reviews = reviewDAO.findAll();
        List<Review> reviewsAddedAfterMinDate = new ArrayList<>();

        for (Review review : reviews)
            if (review.getDateAdded().isAfter(minAddedDate) || review.getDateAdded().isEqual(minAddedDate))
                reviewsAddedAfterMinDate.add(review);

        return reviewsAddedAfterMinDate;
    }

    @Override
    public List<Review> findByMaxAddedDate(LocalDate maxAddedDate) {
        List<Review> reviews = reviewDAO.findAll();
        List<Review> reviewsAddedBeforeMaxDate = new ArrayList<>();

        for (Review review : reviews)
            if (review.getDateAdded().isBefore(maxAddedDate) || review.getDateAdded().isEqual(maxAddedDate))
                reviewsAddedBeforeMaxDate.add(review);

        return reviewsAddedBeforeMaxDate;
    }

    @Override
    public List<Review> findByMinAndMaxAddedDate(LocalDate minAddedDate, LocalDate maxAddedDate) {
        List<Review> reviewsAddedAfterMinDate = findByMinAddedDate(minAddedDate);
        List<Review> reviewsAddedBeforeMaxDate = findByMaxAddedDate(maxAddedDate);

        List<Review> reviewsAddedAfterMinDateAndBeforeMaxDate = reviewsAddedAfterMinDate;
        reviewsAddedAfterMinDateAndBeforeMaxDate.retainAll(reviewsAddedBeforeMaxDate);

        return reviewsAddedAfterMinDateAndBeforeMaxDate;
    }

    @Override
    public void save(Review review)  {
        reviewDAO.save(review);
    }

    @Override
    public void update(Review review) {
        reviewDAO.update(review);
    }

    @Override
    public void delete(Review review) {
        reviewDAO.delete(review);
    }
}
