package com.p4zd4n.bibliothecachudyana.dao.implementation;

import com.p4zd4n.bibliothecachudyana.dao.DiscountDAO;
import com.p4zd4n.bibliothecachudyana.entity.Discount;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DiscountDAOImpl implements DiscountDAO {

    private EntityManager entityManager;

    @Autowired
    public DiscountDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Discount> findAll() {
        return entityManager.createQuery("SELECT d FROM Discount d", Discount.class).getResultList();
    }

    @Transactional
    @Override
    public void save(Discount discount) {
        entityManager.persist(discount);
    }
}
