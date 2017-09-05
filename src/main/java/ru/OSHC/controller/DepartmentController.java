package ru.OSHC.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.OSHC.entity.Department;
import ru.OSHC.service.DepartmentService;

import java.sql.SQLException;
import java.util.List;


@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private static final Logger log = Logger.getLogger(DepartmentController.class);
    private BaseCRUDController<Department> baseCRUDController;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        baseCRUDController = new BaseCRUDController<Department>(departmentService);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void addDepartment(@RequestBody Department department) {
        baseCRUDController.add(department);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@RequestBody Department department) {
        baseCRUDController.update(department);
    }

    @RequestMapping(value = "/clear", method = RequestMethod.GET)
    List getWithNames() {
        return baseCRUDController.getList("getDepartmentWithNames");
    }

    @RequestMapping(method = RequestMethod.GET)
    List getAll(){
        return baseCRUDController.getList("getDepartmentList");
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    Department getElementById(@PathVariable Long id) {
        return baseCRUDController.getById(id, "getDepartmentById");
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable Long id) {
        baseCRUDController.deleteById(id, "getDepartmentById");
    }

}
