package ru.OSHC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    public @ResponseBody List<Certificate> getAllCertificates() {
        try {
            return certificateService.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Certificate getCertificate(@PathVariable long id) {
        try {
            return certificateService.getByID(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addCertificateView(@RequestBody Certificate certificate) {
        try {
            certificateService.add(certificate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeCertificate(@RequestBody Certificate certificate) {
        try {
            certificateService.remove(certificate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCertificateByID(@PathVariable long id) {
        try {
            certificateService.remove(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCertificate(@RequestBody Certificate certificate) {
        try {
            certificateService.update(certificate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
