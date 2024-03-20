package com.p4zd4n.bibliothecachudyana.service;

import com.p4zd4n.bibliothecachudyana.entity.Book;
import com.p4zd4n.bibliothecachudyana.entity.User;

public interface UserService {
    boolean isUserAlreadyRegistered(User user);
    void registerUser(User user);
    void addBookToWishlist(User user, Book book);
    void removeBookFromWishlist(User user, Book book);
    void addBookToCart(User user, Book book);
    void removeBookFromCart(User user, Book book);
}
