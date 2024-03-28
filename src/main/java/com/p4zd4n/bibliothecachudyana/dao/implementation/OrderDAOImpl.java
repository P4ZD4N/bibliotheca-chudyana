package com.p4zd4n.bibliothecachudyana.dao.implementation;

import com.p4zd4n.bibliothecachudyana.dao.OrderDAO;
import com.p4zd4n.bibliothecachudyana.entity.Order;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO {

    private EntityManager entityManager;

    @Autowired
    public OrderDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Order findById(Integer id) {
        return entityManager.find(Order.class, id);
    }

    @Override
    public List<Order> findByUsername(String username) {
        return entityManager.createQuery("SELECT o FROM Order o WHERE o.user.username LIKE :username", Order.class)
                .setParameter("username", username)
                .getResultList();
    }

    @Override
    public List<Order> findAll() {
        return entityManager.createQuery("SELECT o FROM Order o", Order.class).getResultList();
    }

    @Transactional
    @Override
    public void save(Order order) {
        entityManager.persist(order);
    }

    @Transactional
    @Override
    public void update(Order order) {
        entityManager.merge(order);
    }

    @Transactional
    @Override
    public void delete(Order order) {
        entityManager.remove(order);
    }
}
