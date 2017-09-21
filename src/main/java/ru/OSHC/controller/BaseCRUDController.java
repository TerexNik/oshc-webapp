package ru.OSHC.controller;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.OSHC.annotation.Loggable;
import ru.OSHC.service.BaseService;

import java.sql.SQLException;
import java.util.List;

public class BaseCRUDController<T> {
    private BaseService<T> service;

    public BaseCRUDController(BaseService<T> service) {
        this.service = service;
    }

    @Loggable
    T getById(Long id, String namedHQL) throws SQLException {
        return service.getById(id, namedHQL);
    }

    @Loggable
    void deleteById(Long id, String namedHQL) throws SQLException {
        service.removeById(id, namedHQL);
    }

    @Loggable
    List getList(String namedHQL) throws SQLException {
        return service.getAll(namedHQL);
    }

    @Loggable
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Success add")
    void add(@RequestBody T obj) throws SQLException {
        service.add(obj);
    }

    @Loggable
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Success delete")
    void remove(@RequestBody T obj) throws SQLException, ConstraintViolationException {
            service.remove(obj);
    }

    @Loggable
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@RequestBody T obj) throws SQLException {
        service.update(obj);
    }
}
