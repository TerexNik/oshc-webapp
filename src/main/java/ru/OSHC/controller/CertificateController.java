package ru.OSHC.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.OSHC.entity.Certificate;
import ru.OSHC.service.CertificateService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/certificates")
public class CertificateController extends BaseCRUDController<Certificate> {

    private static final Logger log = Logger.getLogger(CertificateController.class);

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    List<Certificate> getWithNames() {
        try {
            return getService().getWithNames("getCertificatesWithNames");
        } catch (SQLException e) {
            return null;
        }
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    Certificate getById(@PathVariable Long id) {
        try {
            return getService().getById(id, "getCertificateById");
        } catch (SQLException e) {
            return null;
        }
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable Long id) {
        try {
            getService().removeById(id, "getCertificateById");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
