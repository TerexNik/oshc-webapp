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
    private BaseCRUDController<Employee> baseCRUDController;
    private EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        baseCRUDController = new BaseCRUDController<Employee>(employeeService);
        service = employeeService;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/change-department", method = RequestMethod.PUT)
    void changeDepartment(@RequestBody List<Department> departments) {
        try {
            service.migrateFromDepAtoDepB(departments.get(0), departments.get(1));
        } catch (SQLException e) {
            log.error("changeDepartment", e);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void addDepartment(@RequestBody Employee employee) {
        baseCRUDController.add(employee);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@RequestBody Employee employee) {
        baseCRUDController.update(employee);
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

    @RequestMapping(value = "/dep/{id}", method = RequestMethod.GET)
    List getEmployeesFromDepartment(@PathVariable Long id) {
        try {
            return service.getEmployeesFromDepartment(id, getAll());
        } catch (SQLException e) {
            log.error("getEmployeesFromDepartment", e);
            return null;
        }
    }

    public EmployeeService getService() {
        return service;
    }
}
