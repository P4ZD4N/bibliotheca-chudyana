package com.p4zd4n.bibliothecachudyana.service;

import com.p4zd4n.bibliothecachudyana.entity.Authority;

public interface AuthorityService {

    void save(Authority authority);
    void cleanUpAuthorities();
}
