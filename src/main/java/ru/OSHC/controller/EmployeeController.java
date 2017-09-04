package ru.OSHC.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.OSHC.entity.Employee;
import ru.OSHC.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController extends BaseCRUDController<Employee> {
    private static final Logger log = Logger.getLogger(EmployeeController.class);

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        setService(employeeService);
    }

    @RequestMapping(value = "/clear",method = RequestMethod.GET)
    List getWithNames() {
        return getList("getEmployeeWithNames");
    }

    @RequestMapping(method = RequestMethod.GET)
    List getAll() {
        return getList("getEmployeeList");
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    Employee getEmployeeById(@PathVariable Long id) {
        return getById(id, "getEmployeeById");
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable Long id) {
        deleteById(id, "getEmployeeById");
    }
}
