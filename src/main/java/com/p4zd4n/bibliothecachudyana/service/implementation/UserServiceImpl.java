package com.p4zd4n.bibliothecachudyana.service.implementation;

import com.p4zd4n.bibliothecachudyana.dao.BookDAO;
import com.p4zd4n.bibliothecachudyana.dao.CartItemDAO;
import com.p4zd4n.bibliothecachudyana.dao.UserDAO;
import com.p4zd4n.bibliothecachudyana.dao.WishlistItemDAO;
import com.p4zd4n.bibliothecachudyana.entity.*;
import com.p4zd4n.bibliothecachudyana.service.UserService;
import com.p4zd4n.bibliothecachudyana.util.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private BookDAO bookDAO;

    private UserDAO userDAO;

    private WishlistItemDAO wishlistItemDAO;

    private CartItemDAO cartItemDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, BookDAO bookDAO, WishlistItemDAO wishlistItemDAO, CartItemDAO cartItemDAO) {
        this.userDAO = userDAO;
        this.bookDAO = bookDAO;
        this.wishlistItemDAO = wishlistItemDAO;
        this.cartItemDAO = cartItemDAO;
    }

    @Override
    public User findById(Integer id) {
        return userDAO.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Override
    public List<User> findByAuthorities(List<String> authorities) {
        return userDAO.findByAuthorities(authorities);
    }

    @Override
    public List<User> findByStatus(Integer status) {
        return userDAO.findByStatus(status);
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public void save(User user) {
        userDAO.save(user);
    }

    @Override
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    public void delete(User user) {
        userDAO.delete(user);
    }

    @Override
    public boolean isUserAlreadyRegistered(User user) {
        User userFoundByUsername = userDAO.findByUsername(user.getUsername());
        User userFoundByEmail = userDAO.findByEmail(user.getEmail());

        return userFoundByUsername != null || userFoundByEmail != null;
    }

    @Override
    public void registerUser(User user) {
        encryptPasswordWithBCrypt(user);
        enableUser(user);
        addDefaultAuthority(user);
        createUserWishlist(user);
        createUserCart(user);
        userDAO.save(user);
    }

    private void encryptPasswordWithBCrypt(User user) {
        String hashedPassword = PasswordEncoder.encodePassword(user.getPassword());
        user.setPassword("{bcrypt}" + hashedPassword);
    }

    private void enableUser(User user) {
        user.setEnabled(1);
    }

    private void addDefaultAuthority(User user) {
        user.addAuthority(new Authority("ROLE_USER", user));
    }

    private void createUserWishlist(User user) {
        Wishlist wishlist = new Wishlist(user);
        wishlist.setItems(new ArrayList<>());
        user.setWishlist(wishlist);
    }

    private void createUserCart(User user) {
        Cart cart = new Cart(user);
        cart.setItems(new ArrayList<>());
        user.setCart(cart);
    }

    @Override
    public void addBookToWishlist(User user, Book book) {
        Wishlist userWishlist = user.getWishlist();
        List<WishlistItem> wishlistItems = userWishlist.getItems();
        WishlistItem newWishlistItem = new WishlistItem(userWishlist, book);

        if (!isNewWishlistItemAlreadyInWishlist(wishlistItems, newWishlistItem))
            wishlistItems.add(newWishlistItem);
    }

    private boolean isNewWishlistItemAlreadyInWishlist(
            List<WishlistItem> wishlistItems, WishlistItem newWishlistItem
    ) {
        for (WishlistItem wishlistItem : wishlistItems) {
            if (wishlistItem.getBook().getId().equals(newWishlistItem.getBook().getId()))
                return true;
        }
        return false;
    }

    @Override
    public void removeBookFromWishlist(User user, Book book) {
        Wishlist userWishlist = user.getWishlist();
        List<WishlistItem> wishlistItems = userWishlist.getItems();

        wishlistItems.removeIf(wishlistItem -> wishlistItem.getBook().equals(book));

        wishlistItemDAO.deleteBookFromWishlist(user, book);
    }

    @Override
    public void addBookToCart(User user, Book book) {
        Cart userCart = user.getCart();
        List<CartItem> cartItems = userCart.getItems();

        CartItem newCartItem = new CartItem(userCart, book);

        if (!isNewCartItemAlreadyInCart(cartItems, newCartItem))
            cartItems.add(newCartItem);
    }

    private boolean isNewCartItemAlreadyInCart(
            List<CartItem> cartItems, CartItem newCartItem
    ) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getBook().getId().equals(newCartItem.getBook().getId()))
                return true;
        }
        return false;
    }

    @Override
    public void removeBookFromCart(User user, Book book) {
        Cart userCart = user.getCart();
        List<CartItem> cartItems = userCart.getItems();

        cartItems.removeIf(cartItem -> cartItem.getBook().equals(book));

        cartItemDAO.deleteBookFromCart(user, book);
    }

    @Override
    public void removeAllBooksFromCart(User user) {
        Cart userCart = user.getCart();
        List<CartItem> cartItems = userCart.getItems();

        cartItems.clear();

        cartItemDAO.deleteAllBooksFromCart(user);
    }
}
