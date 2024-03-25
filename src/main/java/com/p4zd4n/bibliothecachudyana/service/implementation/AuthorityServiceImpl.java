package com.p4zd4n.bibliothecachudyana.service.implementation;

import com.p4zd4n.bibliothecachudyana.dao.AuthorityDAO;
import com.p4zd4n.bibliothecachudyana.entity.Authority;
import com.p4zd4n.bibliothecachudyana.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    private AuthorityDAO authorityDAO;

    @Autowired
    public AuthorityServiceImpl(AuthorityDAO authorityDAO) {
        this.authorityDAO = authorityDAO;
    }

    @Override
    public void save(Authority authority) {
        authorityDAO.save(authority);
    }
}
