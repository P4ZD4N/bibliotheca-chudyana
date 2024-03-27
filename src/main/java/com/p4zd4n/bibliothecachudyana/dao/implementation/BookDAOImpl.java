package com.p4zd4n.bibliothecachudyana.dao.implementation;

import com.p4zd4n.bibliothecachudyana.dao.BookDAO;
import com.p4zd4n.bibliothecachudyana.entity.Book;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO {
    private EntityManager entityManager;

    @Autowired
    public BookDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Book findById(int id) {
        return entityManager.find(Book.class, id);
    }

    @Override
    public List<Book> findByTitle(String title) {
        return entityManager
                .createQuery("SELECT book FROM Book book WHERE book.title LIKE CONCAT('%', :title, '%')")
                .setParameter("title", title)
                .getResultList();
    }

    @Override
    public List<Book> findByAuthorName(String authorName) {
        return entityManager
                .createQuery("SELECT book FROM Book book WHERE book.authorName LIKE CONCAT('%', :authorName, '%')", Book.class)
                .setParameter("authorName", authorName)
                .getResultList();
    }

    @Override
    public List<Book> findByAuthorLastName(String authorLastName) {
        return entityManager
                .createQuery("SELECT book FROM Book book WHERE book.authorLastName LIKE CONCAT('%', :authorLastName, '%')", Book.class)
                .setParameter("authorLastName", authorLastName)
                .getResultList();
    }

    @Override
    public List<Book> findByMinReleaseYear(String minReleaseYear) {
        return entityManager
                .createQuery("SELECT book FROM Book book WHERE " +
                               "SUBSTRING(book.releaseDate, 1, 4) >= :minReleaseYear", Book.class)
                .setParameter("minReleaseYear", minReleaseYear)
                .getResultList();
    }

    @Override
    public List<Book> findByMaxReleaseYear(String maxReleaseYear) {
        return entityManager
                .createQuery("SELECT book FROM Book book WHERE " +
                               "SUBSTRING(book.releaseDate, 1, 4) <= :maxReleaseYear", Book.class)
                .setParameter("maxReleaseYear", maxReleaseYear)
                .getResultList();
    }

    @Override
    public List<Book> findByMinAndMaxReleaseYear(String minReleaseYear, String maxReleaseYear) {
        return entityManager
                .createQuery(
                        "SELECT book FROM Book book WHERE " +
                          "SUBSTRING(book.releaseDate, 1, 4) >= :minReleaseYear AND " +
                          "SUBSTRING(book.releaseDate, 1, 4) <= :maxReleaseYear", Book.class
                )
                .setParameter("minReleaseYear", minReleaseYear)
                .setParameter("maxReleaseYear", maxReleaseYear)
                .getResultList();
    }

    @Override
    public List<Book> findByCategory(String category) {
        return entityManager
                .createQuery("SELECT book FROM Book book WHERE book.category LIKE CONCAT('%', :category, '%')", Book.class)
                .setParameter("category", category)
                .getResultList();
    }

    @Override
    public List<Book> findByMinPrice(Double minPrice) {
        return entityManager
                .createQuery("SELECT book FROM Book book WHERE book.price >= :minPrice", Book.class)
                .setParameter("minPrice", minPrice)
                .getResultList();
    }

    @Override
    public List<Book> findByMaxPrice(Double maxPrice) {
        return entityManager
                .createQuery("SELECT book FROM Book book WHERE book.price <= :maxPrice", Book.class)
                .setParameter("maxPrice", maxPrice)
                .getResultList();
    }

    @Override
    public List<Book> findByMinAndMaxPrice(Double minPrice, Double maxPrice) {
        return entityManager
                .createQuery("SELECT book FROM Book book WHERE book.price >= :minPrice AND book.price <= :maxPrice", Book.class)
                .setParameter("minPrice", minPrice)
                .setParameter("maxPrice", maxPrice)
                .getResultList();
    }

    @Override
    public List<Book> findByMinPages(Integer minPages) {
        return entityManager
                .createQuery("SELECT book FROM Book book WHERE book.numberOfPages >= :minPages", Book.class)
                .setParameter("minPages", minPages)
                .getResultList();
    }

    @Override
    public List<Book> findByMaxPages(Integer maxPages) {
        return entityManager
                .createQuery("SELECT book FROM Book book WHERE book.numberOfPages <= :maxPages", Book.class)
                .setParameter("maxPages", maxPages)
                .getResultList();
    }

    @Override
    public List<Book> findByMinAndMaxPages(Integer minPages, Integer maxPages) {
        return entityManager
                .createQuery("SELECT book FROM Book book WHERE " +
                               "book.numberOfPages >= :minPages AND book.numberOfPages <= :maxPages", Book.class)
                .setParameter("minPages", minPages)
                .setParameter("maxPages", maxPages)
                .getResultList();
    }

    @Override
    public List<Book> findUnavailable() {
        return entityManager
                .createQuery("SELECT book FROM Book book WHERE book.quantityInStock = 0", Book.class)
                .getResultList();
    }

    @Override
    public List<Book> findLastItems() {
        return entityManager
                .createQuery("SELECT book FROM Book book WHERE book.quantityInStock <= 10", Book.class)
                .getResultList();
    }

    @Override
    public List<Book> findAvailable() {
        return entityManager
                .createQuery("SELECT book FROM Book book WHERE book.quantityInStock >= 11", Book.class)
                .getResultList();
    }

    @Override
    public List<Book> findAll() {
        return entityManager.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }

    @Transactional
    @Override
    public void save(Book book) {
        entityManager.persist(book);
    }

    @Transactional
    @Override
    public void update(Book book) {
        entityManager.merge(book);
    }

    @Transactional
    @Override
    public void delete(Book book) {
        entityManager.remove(book);
    }
}
