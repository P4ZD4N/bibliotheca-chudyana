package com.p4zd4n.bibliothecachudyana.dao.implementation;

import com.p4zd4n.bibliothecachudyana.dao.ReviewDAO;
import com.p4zd4n.bibliothecachudyana.entity.Book;
import com.p4zd4n.bibliothecachudyana.entity.Review;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ReviewDAOImpl implements ReviewDAO {

    private EntityManager entityManager;

    @Autowired
    public ReviewDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Review findById(Integer id) {
        return entityManager.find(Review.class, id);
    }

    @Override
    public List<Review> findAll() {
        return entityManager.createQuery("SELECT r FROM Review r", Review.class).getResultList();
    }

    @Override
    public List<Review> findByBook(Book book) {
        String bookTitle = book.getTitle();
        return entityManager.createQuery("SELECT r FROM Review r WHERE r.book.title LIKE :bookTitle", Review.class)
                .setParameter("bookTitle", bookTitle)
                .getResultList();
    }

    @Override
    public List<Review> findByUsername(String username) {
        return entityManager.createQuery("SELECT r FROM Review r WHERE r.user.username LIKE :username", Review.class)
                .setParameter("username", username)
                .getResultList();
    }

    @Override
    public List<Review> findByBookId(Integer bookId) {
        return entityManager.createQuery("SELECT r FROM Review r WHERE r.book.id = :bookId", Review.class)
                .setParameter("bookId", bookId)
                .getResultList();
    }

    @Override
    public List<Review> findByRating(Integer rating) {
        return entityManager.createQuery("SELECT r FROM Review r WHERE r.rating = :rating", Review.class)
                .setParameter("rating", rating)
                .getResultList();
    }

    @Transactional
    @Override
    public void save(Review review) {
        entityManager.persist(review);
    }

    @Transactional
    @Override
    public void update(Review review) {
        entityManager.merge(review);
    }

    @Transactional
    @Override
    public void delete(Review review) {
        entityManager.remove(review);
    }
}
