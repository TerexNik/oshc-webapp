package ru.OSHC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.OSHC.dao.EmployeeDAO;
import ru.OSHC.entity.Employee;
import ru.OSHC.service.EmployeeService;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeDAO employeeService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getEmployees() {
        ModelAndView model = new ModelAndView("employee/main");
        model.addObject("employees", getAllWorkers());
        return model;
    }

    private @ResponseBody List<Employee> getAllWorkers() {
        try {
            return employeeService.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/remove/{id}")
    public void removeEmployee(@PathVariable int id) {
        try {
            employeeService.remove(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addEmployee(@RequestParam Employee employee) {
        try {
            employeeService.add(employee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
