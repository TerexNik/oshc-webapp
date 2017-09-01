package ru.OSHC.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.OSHC.entity.Department;
import ru.OSHC.entity.Employee;
import ru.OSHC.service.BaseService;
import ru.OSHC.service.DepartmentService;

import java.sql.SQLException;
import java.util.List;


@RestController
@RequestMapping("/departments")
public class DepartmentController extends BaseCRUDController<Department> {
    private static final Logger log = Logger.getLogger(DepartmentController.class);
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    List getWithNames() {
        try {
            return getService().getWithNames("getDepartmentWithNames");
        } catch (SQLException e) {
            return null;
        }
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    Department getById(@PathVariable Long id) {
        try {
            return getService().getById(id, "getDepartmentById");
        } catch (SQLException e) {
            return null;
        }
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable Long id) {
        try {
            getService().removeById(id, "getDepartmentById");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
