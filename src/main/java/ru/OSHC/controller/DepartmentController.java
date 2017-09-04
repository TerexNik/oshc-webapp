package ru.OSHC.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.OSHC.entity.Department;
import ru.OSHC.service.DepartmentService;

import java.util.List;


@RestController
@RequestMapping("/departments")
public class DepartmentController extends BaseCRUDController<Department> {
    private static final Logger log = Logger.getLogger(DepartmentController.class);


    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        setService(departmentService);
    }

    @RequestMapping(value = "/clear", method = RequestMethod.GET)
    List getWithNames() {
        return getList("getDepartmentWithNames");
    }

    @RequestMapping(method = RequestMethod.GET)
    List getAll(){
        return getList("getDepartmentList");
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    Department getElementById(@PathVariable Long id) {
        return getById(id, "getDepartmentById");
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable Long id) {
        deleteById(id, "getDepartmentById");
    }

}
