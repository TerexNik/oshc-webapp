package ru.OSHC.security.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.OSHC.model.dao.UserDAO;
import ru.OSHC.util.SessionUtil;

@Service
public class UserService extends SessionUtil implements UserDAO {
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public void save(User user) {

    }

    public User findByName(String name) {
        return null;
    }
}
