package ru.OSHC.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.OSHC.entity.Employee;
import ru.OSHC.service.EmployeeService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController extends BaseCRUDController<Employee> {
    private static final Logger log = Logger.getLogger(EmployeeController.class);

    @RequestMapping(method = RequestMethod.GET)
    List getWithNames() {
        try {
            return getService().getWithNames("getEmployeeWithNames");
        } catch (SQLException e) {
            return null;
        }
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    Employee getById(@PathVariable Long id) {
        try {
            return getService().getById(id, "getEmployeeById");
        } catch (SQLException e) {
            return null;
        }
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable Long id) {
        try {
            getService().removeById(id, "getEmployeeById");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
