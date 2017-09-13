package ru.OSHC.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.OSHC.entity.Scan;
import ru.OSHC.service.ScanService;

import java.util.List;

/**
 * Контроллер скана
 */
@RestController
@RequestMapping("/scans")
public class ScanController {
    private static final Logger log = Logger.getLogger(CertificateController.class);
    private BaseCRUDController<Scan> baseCRUDController;

    @Autowired
    public ScanController(ScanService scanService) {
        baseCRUDController = new BaseCRUDController<Scan>(scanService);
    }

    /**
     * Добавление нового скана.
     * @param scan - скана
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void addScan(@RequestBody Scan scan) {
        baseCRUDController.add(scan);
    }

    /**
     * Изменение данных о скане
     * @param scan - скан
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@RequestBody Scan scan) {
        baseCRUDController.update(scan);
    }

    /**
     * Получение списка сканов
     * @return возвращает список всех сканов
     */
    @RequestMapping(method = RequestMethod.GET)
    List getAll(){
        return baseCRUDController.getList("getScansList");
    }

    /**
     * Получение информации о скане с идентификатором {@link Scan#id}
     * @param id - идентификатор скана
     * @return возвращает выбранный скан
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    Scan getById(@PathVariable Long id) {
        return baseCRUDController.getById(id, "getScanById");
    }

    /**
     * Удаление скана с выбранным идентификатором {@link Scan#id}
     * @param id - идентификатор скана
     */
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable Long id) {
        baseCRUDController.deleteById(id, "getScanById");
    }
}
