package com.p4zd4n.bibliothecachudyana.dao.implementation;

import com.p4zd4n.bibliothecachudyana.dao.WishlistItemDAO;
import com.p4zd4n.bibliothecachudyana.entity.Book;
import com.p4zd4n.bibliothecachudyana.entity.User;
import com.p4zd4n.bibliothecachudyana.entity.WishlistItem;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class WishlistItemDAOImpl implements WishlistItemDAO {

    private EntityManager entityManager;

    @Autowired
    public WishlistItemDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public void deleteBookFromWishlist(User user, Book book) {
        WishlistItem wishlistItem = entityManager.createQuery(
                "SELECT w FROM WishlistItem w WHERE w.book.id = :bookId AND w.wishlist.user.id = :userId", WishlistItem.class
                )
                .setParameter("bookId", book.getId())
                .setParameter("userId", user.getId())
                .getSingleResult();
        entityManager.remove(wishlistItem);
    }
}
