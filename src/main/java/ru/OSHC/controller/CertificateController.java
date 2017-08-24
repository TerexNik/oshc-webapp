package ru.OSHC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.OSHC.entity.Certificate;
import ru.OSHC.service.CertificateService;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/certificate")
public class CertificateController {
    @Autowired
    private CertificateService certificateService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public @ResponseBody List<Certificate> getAllWorkersByDepId() {
        try {
            return certificateService.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
