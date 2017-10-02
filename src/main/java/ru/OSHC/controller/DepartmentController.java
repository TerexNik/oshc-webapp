package ru.OSHC.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.OSHC.annotation.Loggable;
import ru.OSHC.entity.Department;
import ru.OSHC.service.DepartmentService;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.sql.SQLException;
import java.util.List;

/**
 * Контроллер департамента
 */
@CrossOrigin
@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private static final Logger log = Logger.getLogger(DepartmentController.class);
    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * Добавление нового департамента.
     * @param department - департамент
     */
    @Loggable
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void addDepartment(@RequestBody Department department) throws SQLException, PersistenceException {
        departmentService.add(department);
    }

    /**
     * Изменение данных о департаменте
     * @param department - департамент
     */
    @Loggable
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@RequestBody Department department) throws SQLException, PersistenceException {
        departmentService.update(department);
    }

    /**
     * Получение списка департаментов
     * @return возвращает список всех департаментов
     */
    @Loggable
    @RequestMapping(method = RequestMethod.GET)
    List getAll() throws SQLException {
        return departmentService.getAll("getDepartmentList");
    }

    /**
     * Получение списка поддепартаментов
     * @return возвращает список поддепартаментов
     */
    @Loggable
    @RequestMapping(value = "/get/{id}/sub-departments", method = RequestMethod.GET)
    List getSubDepartments(@PathVariable long id) throws SQLException, NoResultException {
        return departmentService.getSubDepartments(id);
    }

    /**
     * Получение информации о департаменте с идентификатором {@link Department#id}
     * @param id - идентификатор департамента
     * @return возвращает выбранный департамент
     */
    @Loggable
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    Department getDepartmentById(@PathVariable Long id) throws SQLException, NoResultException {
        return departmentService.getById(id, "getDepartmentById");
    }

    /**
     * Удаление департамента с выбранным идентификатором {@link Department#id}
     * @param id - идентификатор департамента
     */
    @Loggable
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable Long id) throws SQLException, NoResultException {
        departmentService.deleteById(id);
    }

}
