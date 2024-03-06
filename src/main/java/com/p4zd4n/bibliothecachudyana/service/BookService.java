package com.p4zd4n.bibliothecachudyana.service;

import com.p4zd4n.bibliothecachudyana.entity.Book;

import java.util.List;
import java.util.Map;

public interface BookService {
    Map<String, Integer> getTopCategories();
    List<Book> getNewReleases();
    String getStatusOfBookById(int id);
}
