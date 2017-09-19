package ru.OSHC.security.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import ru.OSHC.model.controller.GlobalControllerAdvice;
import ru.OSHC.security.dao.SecurityDAO;
import ru.OSHC.util.SessionUtil;


@Service
public class SecurityService extends SessionUtil implements SecurityDAO{
    private static final Logger log = org.apache.log4j.Logger.getLogger(GlobalControllerAdvice.class);
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityService(AuthenticationManager authenticationManager, UserService userService, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }


    public void autoLogin(String username, String password) {
    }
}
