package ru.OSHC.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.OSHC.entity.Department;
import ru.OSHC.entity.Employee;
import ru.OSHC.entity.Grade;
import ru.OSHC.entity.Post;
import ru.OSHC.service.EmployeeService;

import java.sql.SQLException;
import java.util.List;

/**
 * Контроллер работника
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private static final Logger log = Logger.getLogger(EmployeeController.class);
    private EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        service = employeeService;
    }

    /**
     * Перемещение всех работников одного департамента в другой
     * @param departments - департаменты
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/change-department", method = RequestMethod.PUT)
    void changeDepartment(@RequestBody List<Department> departments) {
        try {
            service.migrateFromDepAtoDepB(departments.get(0), departments.get(1));
        } catch (SQLException e) {
            log.error("change-department", e);
        }
    }

    /**
     * Изменение грейда у выбранного работника
     * @param newGrade - новый грейд
     * @param employee - работник у которого требуется изменить грейд
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/changeGrade", method = RequestMethod.PUT)
    void changeGrade(@RequestBody Grade newGrade, @RequestBody Employee employee) {
        try {
            service.migrateToNewGrade(newGrade, employee);
        } catch (SQLException e) {
            log.error("changeGrade", e);
        }
    }

    /**
     * Изменение поста у выбранного работника
     * @param newPost - новый пост
     * @param employee - работник у которого требуется изменить пост
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/changePost", method = RequestMethod.PUT)
    void changePost(@RequestBody Post newPost, @RequestBody Employee employee) {
        try {
            service.migrateToNewPost(newPost, employee);
        } catch (SQLException e) {
            log.error("changePost", e);
        }
    }

    /**
     * Добавление нового работника.
     * @param employee - работник
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void addEmployee(@RequestBody Employee employee) {
        try {
            service.add(employee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Изменение данных о работнике
     * @param employee - работник
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@RequestBody Employee employee) {
        try {
            service.update(employee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/clear",method = RequestMethod.GET)
    List getWithNames() {
        try {
            return service.getAll("getEmployeeWithNames");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Получение списка записей о работниках
     * @return возвращает список всех работников
     */
    @RequestMapping(method = RequestMethod.GET)
    List getAll() {
        try {
            return service.getAll("getEmployeeList");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Получение информации о работнике с идентификатором {@link Employee#id}
     * @param id - идентификатор работника
     * @return возвращает выбранного работника
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    Employee getEmployeeById(@PathVariable Long id) {
        try {
            return service.getById(id, "getEmployeeById");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Удаление работника с выбранным идентификатором {@link Employee#id}
     * @param id - идентификатор работника
     */
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable Long id) {
        try {
            service.removeById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Получение списка работников в департаменте с идентификатором {@link Department#id}
     * @param id - идентификатор департамента
     * @return возвращает список всех работников данного департамента
     */
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
