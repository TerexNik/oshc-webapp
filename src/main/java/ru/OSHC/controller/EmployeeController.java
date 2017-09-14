package ru.OSHC.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResponseErrorHandler;
import ru.OSHC.exception.CustomSQLException;
import ru.OSHC.exception.FileNotFoundException;
import ru.OSHC.entity.Department;
import ru.OSHC.entity.Employee;
import ru.OSHC.entity.Grade;
import ru.OSHC.entity.Post;
import ru.OSHC.service.EmployeeService;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.io.IOException;
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
     * @param from - id департамента из которого переводятся сотрудники
     * @param to - id департамента в котороый переводятся сотрудники
     */
    @RequestMapping(value = "/change/department/{from}-{to}", method = RequestMethod.PUT)
    void changeDepartment(@PathVariable long from, @PathVariable long to ) throws SQLException, PersistenceException {
        service.migrateFromDepAtoDepB(from, to);
    }

    /**
     * Изменение грейда у выбранного работника
     * @param newGrade - новый грейд
     * @param id - historyId работника у которого требуется изменить грейд
     */
    @RequestMapping(value = "/change/grade/{id}", method = RequestMethod.PUT)
    void changeGrade(@PathVariable long id, @RequestBody Grade newGrade) throws SQLException, PersistenceException {
        try {
            service.migrateToNewGrade(id, newGrade);
        } catch (NoResultException e) {
            log.error("NoResultException in migrateToNewGrade", e);
            throw new FileNotFoundException("Такого работника не существует");
        }
    }

    /**
     * Изменение поста у выбранного работника
     * @param newPost - новый пост
     * @param id - historyId работника у которого требуется изменить пост
     */
    @RequestMapping(value = "/change/post/{id}", method = RequestMethod.PUT)
    void changePost(@PathVariable long id, @RequestBody Post newPost) throws SQLException, PersistenceException {
        try {
            service.migrateToNewPost(id, newPost);
        } catch (NoResultException e) {
            log.error("NoResultException in migrateToNewPost", e);
            throw new FileNotFoundException("Такого работника не существует");
        }
    }

    /**
     * Добавление нового работника.
     * @param employee - работник
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void addEmployee(@RequestBody Employee employee) throws SQLException {
        service.add(employee);
    }

    /**
     * Изменение данных о работнике
     * @param employee - работник
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@RequestBody Employee employee) throws SQLException {
        service.update(employee);
    }

    /**
     * Получение списка записей о работниках
     * @return возвращает список всех работников
     */
    @RequestMapping(method = RequestMethod.GET)
    public List getAll() throws SQLException {
            return service.getAll("getEmployeeList");
    }

    /**
     * Получение информации о работнике с идентификатором {@link Employee#id}
     * @param id - идентификатор работника
     * @return возвращает выбранного работника
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    Employee getEmployeeById(@PathVariable Long id) throws SQLException {
        try {
            return service.getById(id, "getEmployeeById");
        } catch (NoResultException e) {
            log.error("NoResultException in getEmployeeById", e);
            throw new FileNotFoundException("Такого работника не существует");
        }
    }

    /**
     * Удаление работника с выбранным идентификатором {@link Employee#id}
     * @param id - идентификатор работника
     */
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable Long id) throws SQLException {
        try {
            service.removeById(id);
        } catch (NoResultException e) {
            log.error("NoResultException in deleteEmployeeById", e);
            throw new FileNotFoundException("Такого работника не существует");
        }
    }

    /**
     * Получение списка работников в департаменте с идентификатором {@link Department#id}
     * @param id - идентификатор департамента
     * @return возвращает список всех работников данного департамента
     */
    @RequestMapping(value = "/dep/{id}", method = RequestMethod.GET)
    List getEmployeesFromDepartment(@PathVariable Long id) throws SQLException {
        try {
            return service.getEmployeesFromDepartment(id);
        } catch (NoResultException e) {
            log.error("NoResultException in getEmployeesFromDepartment", e);
            throw new FileNotFoundException("Такого департамента не существует");
        }
    }

    @RequestMapping(value = "/get/part/{letters}" , method = RequestMethod.GET)
    List getEmployeesNamesByLetters(@PathVariable String letters) throws SQLException {
        try {
            return service.getEmployeeByLetters(letters, getAll());
        } catch (NoResultException e) {
            log.error("NoResultException in getEmployeeByLetters", e);
            throw new FileNotFoundException("Работников не найденно");
        }
    }
}
