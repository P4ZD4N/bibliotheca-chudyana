package com.p4zd4n.bibliothecachudyana.dao.implementation;

import com.p4zd4n.bibliothecachudyana.dao.AuthorityDAO;
import com.p4zd4n.bibliothecachudyana.entity.Authority;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AuthorityDAOImpl implements AuthorityDAO {

    private EntityManager entityManager;

    @Autowired
    public AuthorityDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public void save(Authority authority) {
        entityManager.persist(authority);
    }

    @Override
    public List<String> getUserAuthorities(String username) {
        return entityManager.createQuery("SELECT authority.authority FROM Authority authority WHERE authority.username LIKE :username")
                .setParameter("username", username)
                .getResultList();
    }
}
