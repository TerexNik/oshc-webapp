package ru.OSHC.model.controller;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.OSHC.model.service.BaseService;

import java.sql.SQLException;
import java.util.List;

@Controller
public class BaseCRUDController<T> {
    private static final Logger log = Logger.getLogger(BaseCRUDController.class);

    private BaseService<T> service;

    public BaseCRUDController(BaseService<T> service) {
        this.service = service;
    }

    T getById(Long id, String namedHQL) {
        try {
            return service.getById(id, namedHQL);
        } catch (SQLException e) {
            log.error("getById", e);
            return null;
        }
    }

    void deleteById(Long id, String namedHQL) {
        try {
            service.removeById(id, namedHQL);
        } catch (SQLException e) {
            log.error("deleteById", e);
            e.printStackTrace();
        }
    }

    List getList(String namedHQL) {
        try {
            return service.getAll(namedHQL);
        } catch (SQLException e) {
            log.error("getList", e);
            return null;
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Success add")
    void add(@RequestBody T obj) {
        try {
            service.add(obj);
        } catch (SQLException e) {
                    log.error("add", e);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Success delete")
    void remove(@RequestBody T obj) {
        try {
            service.remove(obj);
        } catch (SQLException e) {
            log.error("remove SQLEx", e);
        } catch (ConstraintViolationException e) {
            log.error("remove CVEx", e);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@RequestBody T obj) {
        try {
            service.update(obj);
        } catch (SQLException e) {
            log.error("update", e);
        }
    }



    void setService(BaseService<T> service) {
        this.service = service;
    }
}
