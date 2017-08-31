package ru.OSHC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.OSHC.entity.Employee;
import ru.OSHC.service.EmployeeService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.GET)
    private List<Employee> getAllEmployee() {
        try {
            return employeeService.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Employee getEmployee(@PathVariable long id) {
        try {
            return employeeService.getById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addEmployeeView(@RequestBody Employee employee) {
        try {
            employeeService.add(employee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeEmployee(@RequestBody Employee employee) {
        try {
            employeeService.remove(employee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEmployee(@RequestBody Employee employee) {
        try {
            employeeService.update(employee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
