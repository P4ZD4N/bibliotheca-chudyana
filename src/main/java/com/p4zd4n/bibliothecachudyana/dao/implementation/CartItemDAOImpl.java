package com.p4zd4n.bibliothecachudyana.dao.implementation;

import com.p4zd4n.bibliothecachudyana.dao.CartItemDAO;
import com.p4zd4n.bibliothecachudyana.entity.*;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CartItemDAOImpl implements CartItemDAO {

    private EntityManager entityManager;

    @Autowired
    public CartItemDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public void deleteBookFromCart(User user, Book book) {
        CartItem cartItem = entityManager.createQuery(
                        "SELECT c FROM CartItem c WHERE c.book.id = :bookId AND c.cart.user.id = :userId", CartItem.class
                )
                .setParameter("bookId", book.getId())
                .setParameter("userId", user.getId())
                .getSingleResult();
        entityManager.remove(cartItem);
    }

    @Transactional
    @Override
    public void deleteAllBooksFromCart(User user) {
        Cart userCart = user.getCart();

        entityManager.createQuery("DELETE FROM CartItem ci WHERE ci.cart = :cart")
                .setParameter("cart", userCart)
                .executeUpdate();
    }
}
