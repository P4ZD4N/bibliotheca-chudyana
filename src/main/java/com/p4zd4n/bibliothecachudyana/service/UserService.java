package com.p4zd4n.bibliothecachudyana.service;

import com.p4zd4n.bibliothecachudyana.entity.User;

public interface UserService {
    boolean isUserAlreadyRegistered(User user);
    void registerUser(User user);
}
