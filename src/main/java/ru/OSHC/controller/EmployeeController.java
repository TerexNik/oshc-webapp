package ru.OSHC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.OSHC.entity.Employee;
import ru.OSHC.service.EmployeeService;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getEmployees() {
        ModelAndView model = new ModelAndView("employee/main");
        model.addObject("employees", getAllWorkers());
        return model;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public @ResponseBody List<Employee> getAllWorkers() {
        try {
            return employeeService.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
