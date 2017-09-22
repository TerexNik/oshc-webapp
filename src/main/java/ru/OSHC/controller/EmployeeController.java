package ru.OSHC.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.OSHC.annotation.Loggable;
import ru.OSHC.exception.FileNotFoundException;
import ru.OSHC.entity.Department;
import ru.OSHC.entity.Employee;
import ru.OSHC.entity.Grade;
import ru.OSHC.entity.Post;
import ru.OSHC.service.EmployeeService;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
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
     * Добавление нового работника.
     * @param employee - работник
     */
    @Loggable
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void addEmployee(@RequestBody Employee employee) throws SQLException {
        service.add(employee);
    }

    /**
     * Перемещение всех работников одного департамента в другой
     * @param from - id департамента из которого переводятся сотрудники
     * @param to - id департамента в котороый переводятся сотрудники
     */
    @Loggable
    @RequestMapping(value = "/change/department/{from}-{to}", method = RequestMethod.PUT)
    void changeDepartment(@PathVariable long from, @PathVariable long to ) throws SQLException, PersistenceException, NoResultException {
        service.migrateFromDepAtoDepB(from, to);
    }

    /**
     * Изменение грейда у выбранного работника
     * @param newGrade - новый грейд
     * @param id - historyId работника у которого требуется изменить грейд
     */
    @Loggable
    @RequestMapping(value = "/change/grade/{id}", method = RequestMethod.PUT)
    void changeGrade(@PathVariable long id, @RequestBody Grade newGrade) throws SQLException, PersistenceException, NoResultException {
        service.migrateToNewGrade(id, newGrade);
    }

    /**
     * Изменение поста у выбранного работника
     * @param newPost - новый пост
     * @param id - historyId работника у которого требуется изменить пост
     */
    @Loggable
    @RequestMapping(value = "/change/post/{id}", method = RequestMethod.PUT)
    void changePost(@PathVariable long id, @RequestBody Post newPost) throws SQLException, PersistenceException {
        service.migrateToNewPost(id, newPost);
    }

    /**
     * Изменение данных о работнике
     * @param employee - работник
     */
    @Loggable
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@RequestBody Employee employee) throws SQLException {
        service.update(employee);
    }

    /**
     * Удаление работника с выбранным идентификатором {@link Employee#id}
     * @param id - идентификатор работника
     */
    @Loggable
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable Long id) throws SQLException, NoResultException {
        service.removeById(id);
    }

    /**
     * Получение списка записей о работниках
     * @return возвращает список всех работников
     */
    @Loggable
    @RequestMapping(method = RequestMethod.GET)
    public List getAll() throws SQLException {
        return service.getAll("getEmployeeList");
    }

    /**
     * Получение информации о работнике с идентификатором {@link Employee#id}
     * @param id - идентификатор работника
     * @return возвращает выбранного работника
     */
    @Loggable
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    Employee getEmployeeById(@PathVariable Long id) throws SQLException, NoResultException {
        return service.getById(id, "getEmployeeById");
    }

    /**
     * Получение списка работников в департаменте с идентификатором {@link Department#id}
     * @param id - идентификатор департамента
     * @return возвращает список всех работников данного департамента
     */
    @Loggable
    @RequestMapping(value = "/dep/{id}", method = RequestMethod.GET)
    List getEmployeesFromDepartment(@PathVariable Long id) throws SQLException, NoResultException {
        return service.getEmployeesFromDepartment(id);
    }

    @Loggable
    @RequestMapping(value = "/get/part/{letters}" , method = RequestMethod.GET)
    List getEmployeesNamesByLetters(@PathVariable String letters) throws SQLException, NoResultException {
        return service.getEmployeeByLetters(letters, getAll());
    }

    @Loggable
    @RequestMapping(value = "/get/history/{id}" , method = RequestMethod.GET)
    List getEmployeesHistory(@PathVariable long id) throws SQLException, NoResultException {
        return service.getEmployeeHistory(id);
    }
}
