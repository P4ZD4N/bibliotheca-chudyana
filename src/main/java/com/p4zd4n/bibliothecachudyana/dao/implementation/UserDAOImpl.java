package com.p4zd4n.bibliothecachudyana.dao.implementation;

import com.p4zd4n.bibliothecachudyana.dao.UserDAO;
import com.p4zd4n.bibliothecachudyana.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    private EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User findById(Integer id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> findByAuthorities(List<String> authorities) {
        List<User> users = new ArrayList<>();

        for (int i = 0; i < authorities.size(); i++) {
            String authority = authorities.get(i);
            if (i == 0) {
                users.addAll(entityManager.createQuery("SELECT a.user FROM Authority a WHERE a.authority LIKE :authority")
                        .setParameter("authority", authority)
                        .getResultList());
            } else {
                users.retainAll(entityManager.createQuery("SELECT a.user FROM Authority a WHERE a.authority LIKE :authority")
                        .setParameter("authority", authority)
                        .getResultList());
            }
            i++;
        }

        return users;
    }

    @Override
    public List<User> findByStatus(Integer status) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.enabled = :status")
                .setParameter("status", status)
                .getResultList();
    }

    @Override
    public User findByUsername(String username) {
        try {
            return entityManager.createQuery("SELECT user FROM User user WHERE user.username LIKE CONCAT('%', :username, '%')", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("SELECT user FROM User user", User.class).getResultList();
    }

    @Transactional
    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Transactional
    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Transactional
    @Override
    public void delete(User user) {
        entityManager.remove(user);
    }
}
