package ru.OSHC.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.OSHC.annotation.Loggable;
import ru.OSHC.entity.Scan;
import ru.OSHC.exception.FileNotFoundException;
import ru.OSHC.service.ScanService;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

/**
 * Контроллер скана
 */
@CrossOrigin
@RestController
@RequestMapping("/scans")
public class ScanController {
    private static final Logger log = Logger.getLogger(CertificateController.class);
    private final ScanService scanService;

    @Autowired
    public ScanController(ScanService scanService) {
        this.scanService = scanService;
    }


    /**
     * Добавление нового скана.
     * @param scan - скана
     */
    @Loggable
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void addScan(@RequestBody Scan scan) throws SQLException {
        scanService.add(scan);
    }

    /**
     * Изменение данных о скане
     * @param scan - скан
     */
    @Loggable
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@RequestBody Scan scan) throws SQLException {
        scanService.update(scan);
    }

    /**
     * Получение списка сканов
     * @return возвращает список всех сканов
     */
    @Loggable
    @RequestMapping(method = RequestMethod.GET)
    List getAll() throws SQLException {
        return scanService.getAll("getScansList");
    }

    /**
     * Получение информации о скане с идентификатором {@link Scan#id}
     * @param id - идентификатор скана
     * @return возвращает выбранный скан
     */
    @Loggable
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    Scan getById(@PathVariable Long id) throws SQLException, NoResultException {
        return scanService.getById(id, "getScanById");
    }

    /**
     * Удаление скана с выбранным идентификатором {@link Scan#id}
     * @param id - идентификатор скана
     */
    @Loggable
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable Long id) throws SQLException, NoResultException {
        scanService.removeById(id, "getScanById");
    }
}
