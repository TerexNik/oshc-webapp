package ru.OSHC.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.OSHC.entity.Department;
import ru.OSHC.entity.Employee;
import ru.OSHC.service.EmployeeService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private static final Logger log = Logger.getLogger(EmployeeController.class);
    private EmployeeService employeeService;
    private BaseCRUDController<Employee> baseCRUDController = new BaseCRUDController<Employee>(employeeService);


    @RequestMapping(value = "/changeDepartment", method = RequestMethod.PUT)
    void changeDepartment(@RequestBody Department from, @RequestBody Department to) {
        try {
            employeeService.migrateFromDepAtoDepB(from, to);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/clear",method = RequestMethod.GET)
    List getWithNames() {
        return baseCRUDController.getList("getEmployeeWithNames");
    }


    @RequestMapping(method = RequestMethod.GET)
    List getAll() {
        return baseCRUDController.getList("getEmployeeList");
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    Employee getEmployeeById(@PathVariable Long id) {
        return baseCRUDController.getById(id, "getEmployeeById");
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable Long id) {
        baseCRUDController.deleteById(id, "getEmployeeById");
    }
}
