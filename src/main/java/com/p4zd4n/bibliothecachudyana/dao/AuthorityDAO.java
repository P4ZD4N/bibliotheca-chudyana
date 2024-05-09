package com.p4zd4n.bibliothecachudyana.dao;

import com.p4zd4n.bibliothecachudyana.entity.Authority;

public interface AuthorityDAO {

    void cleanUpAuthorities();
    void save(Authority authority);
}
