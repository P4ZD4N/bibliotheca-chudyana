package com.p4zd4n.bibliothecachudyana.dao;

import com.p4zd4n.bibliothecachudyana.entity.Book;

import java.util.List;

public interface BookDAO {
    Book findById(int id);
    List<Book> findByTitle(String title);
    List<Book> findByAuthorName(String authorFirstName);
    List<Book> findByAuthorLastName(String authorLastName);
    List<Book> findByReleaseYear(String publicationYear);
    List<Book> findByCategory(String category);
    List<Book> findAll();
    void save(Book book);
    void update(Book book);
    void delete(Book book);
}
