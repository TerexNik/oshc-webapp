package ru.OSHC.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.OSHC.entity.Scan;
import ru.OSHC.service.ScanService;

import java.sql.SQLException;
import java.util.List;

/**
 * Контроллер скана
 */
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
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void addScan(@RequestBody Scan scan) {
        try {
            scanService.add(scan);
        } catch (SQLException e) {
            log.error("addScan", e);
        }
    }

    /**
     * Изменение данных о скане
     * @param scan - скан
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@RequestBody Scan scan) {
        try {
            scanService.update(scan);
        } catch (SQLException e) {
            log.error("updateScan", e);
        }
    }

    /**
     * Получение списка сканов
     * @return возвращает список всех сканов
     */
    @RequestMapping(method = RequestMethod.GET)
    List getAll(){
        try {
            return scanService.getAll("getScansList");
        } catch (SQLException e) {
            log.error("getScansList", e);
            return null;
        }
    }

    /**
     * Получение информации о скане с идентификатором {@link Scan#id}
     * @param id - идентификатор скана
     * @return возвращает выбранный скан
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    Scan getById(@PathVariable Long id) {
        try {
            return scanService.getById(id);
        } catch (SQLException e) {
            log.error("getScanById", e);
            return null;
        }
    }

    /**
     * Удаление скана с выбранным идентификатором {@link Scan#id}
     * @param id - идентификатор скана
     */
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable Long id) {
        try {
            scanService.removeById(id, "getScanById");
        } catch (SQLException e) {
            log.error("deleteScanById", e);
        }
    }
}
