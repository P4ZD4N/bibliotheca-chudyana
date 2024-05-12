package com.p4zd4n.bibliothecachudyana.service;

import com.p4zd4n.bibliothecachudyana.entity.Book;
import com.p4zd4n.bibliothecachudyana.entity.Cart;
import com.p4zd4n.bibliothecachudyana.entity.CartItem;
import com.p4zd4n.bibliothecachudyana.entity.User;

import java.util.List;

public interface UserService {

    User findById(Integer id);
    User findByUsername(String username);

    Double getCartValue(List<CartItem> cartItems);

    List<User> findByAuthorities(List<String> authorities);
    List<User> findByStatus(Integer status);
    List<User> findAll();

    boolean isUserAlreadyRegistered(User user);
    boolean isUnauthorizedToEnter(String username, String authenticatedUsername);
    boolean hasUserEmployeeAuthority(User user);
    boolean hasUserManagerAuthority(User user);
    boolean hasUserAdminAuthority(User user);

    void registerUser(User user);
    void addBookToWishlist(User user, Book book);
    void addBookToCart(User user, Book book);
    void removeBookFromWishlist(User user, Book book);
    void removeBookFromCart(User user, Book book);
    void removeAllBooksFromCart(User user);
    void save(User user);
    void update(User user);
    void delete(User user);
}
