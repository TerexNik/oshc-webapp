package ru.OSHC.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.OSHC.service.BaseService;

import java.sql.SQLException;
import java.util.List;

public abstract class BaseCRUDController<T> {

    private BaseService<T> service;

    T getById(Long id, String namedHQL) {
        try {
            return service.getById(id, namedHQL);
        } catch (SQLException e) {
            return null;
        }
    }

    void deleteById(Long id, String namedHQL) {
        try {
            service.removeById(id, namedHQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    List getList(String namedHQL) {
        try {
            return service.getAll(namedHQL);
        } catch (SQLException e) {
            return null;
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void add(@RequestBody T obj) {
        try {
            service.add(obj);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void remove(@RequestBody T obj) {
        try {
            service.remove(obj);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@RequestBody T obj) {
        try {
            service.update(obj);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void setService(BaseService<T> service) {
        this.service = service;
    }
}
