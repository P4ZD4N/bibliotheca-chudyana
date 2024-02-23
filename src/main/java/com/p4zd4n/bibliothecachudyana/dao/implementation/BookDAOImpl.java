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
    public List<Book> findByReleaseYear(String releaseYear) {
        return entityManager
                .createQuery("SELECT book FROM Book book WHERE FUNCTION('YEAR', book.releaseDate) = :year", Book.class)
                .setParameter("year", releaseYear)
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

    @Override
    public void delete(Book book) {
        entityManager.remove(book);
    }
}
