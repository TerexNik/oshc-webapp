package ru.OSHC.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResponseErrorHandler;
import ru.OSHC.exception.FileNotFoundException;
import ru.OSHC.entity.Department;
import ru.OSHC.entity.Employee;
import ru.OSHC.entity.Grade;
import ru.OSHC.entity.Post;
import ru.OSHC.service.EmployeeService;

import javax.persistence.NoResultException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Контроллер работника
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController implements ResponseErrorHandler {
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
    @RequestMapping(value = "/change-department/{from}-{to}", method = RequestMethod.PUT)
    void changeDepartment(@PathVariable long from, @PathVariable long to ) {
        try {
            service.migrateFromDepAtoDepB(from, to);
        } catch (SQLException e) {
            log.error("change-department", e);
        }
    }

    /**
     * Изменение грейда у выбранного работника
     * @param newGrade - новый грейд
     * @param id - historyId работника у которого требуется изменить грейд
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/changeGrade/{id}", method = RequestMethod.PUT)
    void changeGrade(@PathVariable long id, @RequestBody Grade newGrade) {
        try {
            service.migrateToNewGrade(id, newGrade);
        } catch (SQLException e) {
            log.error("changeGrade", e);
        }
    }

    /**
     * Изменение поста у выбранного работника
     * @param newPost - новый пост
     * @param id - historyId работника у которого требуется изменить пост
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/changePost", method = RequestMethod.PUT)
    void changePost(@PathVariable long id, @RequestBody Post newPost) {
        try {
            service.migrateToNewPost(id, newPost);
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
            log.error("addEmployee", e);
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
            log.error("updateEmployee", e);
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
            log.error("getEmployeeList", e);
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
            log.error("SQLException in getEmployeeById", e);
            throw new FileNotFoundException();
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
    void deleteById(@PathVariable Long id) {
        try {
            service.removeById(id);
        } catch (SQLException e) {
            log.error("deleteEmployeeById", e);
        } catch (NoResultException e) {
            log.error("NoResultException in getEmployeeById", e);
            throw new FileNotFoundException("Такого работника не существует");
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
            return service.getEmployeesFromDepartment(id);
        } catch (SQLException e) {
            log.error("getEmployeesFromDepartment", e);
            return null;
        }
    }

    @RequestMapping(value = "/get/part/{letters}" , method = RequestMethod.GET)
    List getEmployeesNamesByLetters(@PathVariable String letters) {
        try {
            return service.getEmployeeByLetters(letters, getAll());
        } catch (Exception e) {
            log.error("getEmployeesNamesByChars", e);
            return  null;
        }
    }

    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return false;
    }

    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {

    }
}
