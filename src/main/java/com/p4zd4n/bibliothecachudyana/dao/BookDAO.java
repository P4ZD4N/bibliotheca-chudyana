package com.p4zd4n.bibliothecachudyana.dao;

import com.p4zd4n.bibliothecachudyana.entity.Book;

import java.util.List;

public interface BookDAO {
    Book findById(int id);
    List<Book> findByTitle(String title);
    List<Book> findByAuthorName(String authorFirstName);
    List<Book> findByAuthorLastName(String authorLastName);
    List<Book> findByMinReleaseYear(String minReleaseYear);
    List<Book> findByMaxReleaseYear(String maxReleaseYear);
    List<Book> findByMinAndMaxReleaseYear(String minReleaseYear, String maxReleaseYear);
    List<Book> findByCategory(String category);
    List<Book> findByMinPrice(Double minPrice);
    List<Book> findByMaxPrice(Double maxPrice);
    List<Book> findByMinAndMaxPrice(Double minPrice, Double maxPrice);
    List<Book> findByMinPages(Integer minPages);
    List<Book> findByMaxPages(Integer maxPages);
    List<Book> findByMinAndMaxPages(Integer minPages, Integer maxPages);
    List<Book> findUnavailable();
    List<Book> findLastItems();
    List<Book> findAvailable();
    List<Book> findAll();
    void save(Book book);
    void update(Book book);
    void delete(Book book);
}
