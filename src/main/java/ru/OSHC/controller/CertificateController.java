package ru.OSHC.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.OSHC.entity.Certificate;
import ru.OSHC.service.CertificateService;

import java.sql.SQLException;
import java.util.List;

/**
 * Контроллер сертификата
 */
@RestController
@RequestMapping("/certificates")
public class CertificateController {
    private static final Logger log = Logger.getLogger(CertificateController.class);
    private CertificateService certificateService;

    @Autowired
    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    /**
     * Добавление нового сертификата.
     * @param certificate - сертификат
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void addCertificate(@RequestBody Certificate certificate) {
        try {
            certificateService.add(certificate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Изменение данных о сертификате
     * @param certificate - сертификат
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@RequestBody Certificate certificate) {
        try {
            certificateService.update(certificate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Получение списка сертификатов
     * @return возвращает список всех сертификатов
     */
    @RequestMapping(method = RequestMethod.GET)
    List getAll(){
        try {
            return certificateService.getAll("getCertificateList");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Получение информации о сертификате с идентификатором {@link Certificate#id}
     * @param id - идентификатор сертификата
     * @return возвращает выбранный сертификат
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    Certificate getById(@PathVariable Long id) {
        try {
            return certificateService.getById(id, "getCertificateById");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Удаление сертификата с выбранным идентификатором {@link Certificate#id}
     * @param id - идентификатор сертификата
     */
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable Long id) {
        try {
            certificateService.removeById(id, "getCertificateById");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
