package ru.OSHC.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SecurityController {
    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public ResponseEntity authentication() {
        return null;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity registration() {
        return null;
    }
}
