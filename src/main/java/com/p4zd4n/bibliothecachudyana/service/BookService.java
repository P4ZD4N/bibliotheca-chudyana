package com.p4zd4n.bibliothecachudyana.service;

import com.p4zd4n.bibliothecachudyana.entity.Book;
import com.p4zd4n.bibliothecachudyana.enums.BookStatus;

import java.util.List;
import java.util.Map;

public interface BookService {

    Book findById(int id);

    List<Book> findByTitle(String title);
    List<Book> findByAuthorName(String authorFirstName);
    List<Book> findByAuthorLastName(String authorLastName);
    List<Book> findByMinReleaseYear(Integer minReleaseYear);
    List<Book> findByMaxReleaseYear(Integer maxReleaseYear);
    List<Book> findByMinAndMaxReleaseYear(Integer minReleaseYear, Integer maxReleaseYear);
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
    List<Book> getNewReleases();

    Map<String, Integer> getTopCategories();

    BookStatus getStatusOfBookById(int id);

    void save(Book book);
    void update(Book book);
    void delete(Book book);
}
