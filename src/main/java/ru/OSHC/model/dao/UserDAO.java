package ru.OSHC.model.dao;

import org.springframework.security.core.userdetails.User;

public interface UserDAO {
    void save(User user);
    User findByName(String name);
}
