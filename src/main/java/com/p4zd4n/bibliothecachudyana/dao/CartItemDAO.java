package com.p4zd4n.bibliothecachudyana.dao;

import com.p4zd4n.bibliothecachudyana.entity.Book;
import com.p4zd4n.bibliothecachudyana.entity.User;

public interface CartItemDAO {

    void deleteBookFromCart(User user, Book book);
    void deleteAllBooksFromCart(User user);
}
