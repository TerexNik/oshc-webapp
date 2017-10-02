package ru.OSHC.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.OSHC.annotation.Loggable;
import ru.OSHC.entity.Certificate;
import ru.OSHC.exception.FileNotFoundException;
import ru.OSHC.service.CertificateService;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

/**
 * Контроллер сертификата
 */
@CrossOrigin
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
    @Loggable
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void addCertificate(@RequestBody Certificate certificate) throws SQLException {
        certificateService.add(certificate);
    }

    /**
     * Изменение данных о сертификате
     * @param certificate - сертификат
     */
    @Loggable
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@RequestBody Certificate certificate) throws SQLException {
        certificateService.update(certificate);
    }

    /**
     * Получение списка сертификатов
     * @return возвращает список всех сертификатов
     */
    @Loggable
    @RequestMapping(method = RequestMethod.GET)
    List getAll() throws SQLException {
        return certificateService.getAll("getCertificateList");
    }

    /**
     * Получение информации о сертификате с идентификатором {@link Certificate#id}
     * @param id - идентификатор сертификата
     * @return возвращает выбранный сертификат
     */
    @Loggable
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    Certificate getById(@PathVariable Long id) throws SQLException, NoResultException{
        return certificateService.getById(id, "getCertificateById");
    }

    /**
     * Удаление сертификата с выбранным идентификатором {@link Certificate#id}
     * @param id - идентификатор сертификата
     */
    @Loggable
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable Long id) throws SQLException, NoResultException {
        certificateService.removeById(id, "getCertificateById");
    }
}
