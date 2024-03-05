package com.p4zd4n.bibliothecachudyana.dao;

import com.p4zd4n.bibliothecachudyana.entity.Authority;

import java.util.List;

public interface AuthorityDAO {
    void save(Authority authority);
    List<String> getUserAuthorities(String username);
}
