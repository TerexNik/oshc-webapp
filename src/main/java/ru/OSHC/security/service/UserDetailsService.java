package ru.OSHC.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.OSHC.model.dao.UserDAO;
import ru.OSHC.util.SessionUtil;

@Service
public class UserDetailsService extends SessionUtil {
    private final UserDAO userDAO;

    @Autowired
    public UserDetailsService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return null;
    }
}
