package ru.OSHC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView getEmployees() {
        ModelAndView model = new ModelAndView("employee/main");
        model.addObject("employees", getAllWorkers());
        return model;
    }

    private List<Employee> getAllWorkers() {
        try {
            return employeeService.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/remove/{id}")
    public ModelAndView removeEmployee(@PathVariable("id") int id) {
        try {
            employeeService.remove(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ModelAndView("employee/error");
        }
        return getEmployees();
    }

    @RequestMapping(value = "/add")
    public ModelAndView addEmployeeView(Employee employee) {
        ModelAndView model = new ModelAndView("employee/add");
        model.addObject("employee", new Employee());
        return model;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addEmployee(@ModelAttribute("employee") Employee employee, BindingResult result) {
            if (result.hasErrors()) {
            return "employee/error";
        }

        try {
            employeeService.add(employee);
        } catch (SQLException e) {
            e.printStackTrace();
            return "employee/error";
        }
        return "redirect:/";
    }
}
