package com.p4zd4n.bibliothecachudyana.service.implementation;

import com.p4zd4n.bibliothecachudyana.dao.BookDAO;
import com.p4zd4n.bibliothecachudyana.dao.UserDAO;
import com.p4zd4n.bibliothecachudyana.entity.Authority;
import com.p4zd4n.bibliothecachudyana.entity.User;
import com.p4zd4n.bibliothecachudyana.entity.Wishlist;
import com.p4zd4n.bibliothecachudyana.entity.WishlistItem;
import com.p4zd4n.bibliothecachudyana.service.UserService;
import com.p4zd4n.bibliothecachudyana.util.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    private BookDAO bookDAO;

    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, BookDAO bookDAO) {
        this.userDAO = userDAO;
        this.bookDAO = bookDAO;
    }

    @Override
    public boolean isUserAlreadyRegistered(User user) {
        User existingUser = userDAO.findByUsername(user.getUsername());
        return existingUser != null;
    }

    @Override
    public void registerUser(User user) {
        encryptPasswordWithBCrypt(user);
        enableUser(user);
        addDefaultAuthority(user);
        createUserWishlist(user);
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
        // user.getWishlist().getItems().add(new WishlistItem(wishlist, bookDAO.findById(1)));
    }
}
