package ru.OSHC.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.OSHC.entity.Certificate;
import ru.OSHC.service.CertificateService;
import ru.OSHC.service.DepartmentService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/certificates")
public class CertificateController extends BaseCRUDController<Certificate> {
    private static final Logger log = Logger.getLogger(CertificateController.class);

    @Autowired
    public CertificateController(CertificateService certificateService) {
        setService(certificateService);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    List<Certificate> getWithNames() {
        return getList("getCertificatesWithNames");
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    Certificate getById(@PathVariable Long id) {
        return getById(id, "getCertificateById");
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable Long id) {
            deleteById(id, "getCertificateById");
    }
}
