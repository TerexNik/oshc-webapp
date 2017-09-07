package ru.OSHC.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.OSHC.entity.History;
import ru.OSHC.service.HistoryService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value = "/history")
public class HistoryController {

    private static final Logger log = Logger.getLogger(GradeController.class);

    @Autowired
    HistoryService historyService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void add(@RequestBody History history) {
        try {
            historyService.add(history);
        } catch (SQLException e) {
            log.error("add", e);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@RequestBody History history) {
        try {
            historyService.update(history);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    List getAll() {
        try {
            return historyService.getAll("getHistoryList");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    History getById(@PathVariable Long id) {
        try {
            return historyService.getById(id, "getHistoryById");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable Long id) {
        try {
            historyService.removeById(id, "getHistoryById");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
