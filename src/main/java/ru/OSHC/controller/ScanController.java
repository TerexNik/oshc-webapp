package ru.OSHC.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.OSHC.entity.Scan;
import ru.OSHC.service.ScanService;

import java.util.List;

@RestController
@RequestMapping("/scans")
public class ScanController {
    private static final Logger log = Logger.getLogger(CertificateController.class);
    private BaseCRUDController<Scan> baseCRUDController;

    @Autowired
    public ScanController(ScanService scanService) {
        baseCRUDController = new BaseCRUDController<Scan>(scanService);
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void addScan(@RequestBody Scan scan) {
        baseCRUDController.add(scan);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@RequestBody Scan scan) {
        baseCRUDController.update(scan);
    }


    @RequestMapping(value = "/get", method = RequestMethod.GET)
    List getWithNames() {
        return baseCRUDController.getList("getScans");
    }

    @RequestMapping(method = RequestMethod.GET)
    List getAll(){
        return baseCRUDController.getList("getScansList");
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    Scan getById(@PathVariable Long id) {
        return baseCRUDController.getById(id, "getScanById");
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable long id) {
        baseCRUDController.deleteById(id, "getScanById");
    }
}
