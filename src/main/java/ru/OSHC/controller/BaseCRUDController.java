package ru.OSHC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.OSHC.service.BaseService;

import java.lang.annotation.Inherited;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseCRUDController<T> {

    private BaseService<T> service;

    abstract List getWithNames();

    abstract T getById(@PathVariable Long id);

    abstract void deleteById(@PathVariable Long id);

    @RequestMapping(value = "/add", method = RequestMethod.POST)
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

    public BaseService<T> getService() {
        return service;
    }

    @Autowired
    public void setService(BaseService<T> service) {
        this.service = service;
    }
}
