package com.p4zd4n.bibliothecachudyana.service;

import com.p4zd4n.bibliothecachudyana.entity.Authority;

public interface AuthorityService {

    void cleanUpAuthorities();
    void save(Authority authority);
}
