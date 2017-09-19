package ru.OSHC.model.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.OSHC.model.entity.Department;
import ru.OSHC.exception.FileNotFoundException;
import ru.OSHC.model.service.DepartmentService;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.sql.SQLException;
import java.util.List;

/**
 * Контроллер департамента
 */
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
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void addDepartment(@RequestBody Department department) throws SQLException, PersistenceException {
        departmentService.add(department);
    }

    /**
     * Изменение данных о департаменте
     * @param department - департамент
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@RequestBody Department department) throws SQLException, PersistenceException {
        departmentService.update(department);
    }

    /**
     * Получение списка департаментов
     * @return возвращает список всех департаментов
     */
    @RequestMapping(method = RequestMethod.GET)
    List getAll() throws SQLException {
        return departmentService.getAll("getDepartmentList");
    }

    /**
     * Получение списка поддепартаментов
     * @return возвращает список поддепартаментов
     */
    @RequestMapping(value = "/get/{id}/sub-departments", method = RequestMethod.GET)
    List getSubDepartments(@PathVariable long id) throws SQLException {
        try {
            return departmentService.getSubDepartments(id);
        } catch (NoResultException e) {
            log.error("NoResultException in getSubDepartments", e);
            throw new FileNotFoundException("Такого отдела не существует");
        }
    }

    /**
     * Получение информации о департаменте с идентификатором {@link Department#id}
     * @param id - идентификатор департамента
     * @return возвращает выбранный департамент
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    Department getDepartmentById(@PathVariable Long id) throws SQLException {
        try {
            return departmentService.getById(id, "getDepartmentById");
        } catch (NoResultException e) {
            log.error("NoResultException in getDepartmentById", e);
            throw new FileNotFoundException("Такого отдела не существует");
        }
    }

    /**
     * Удаление департамента с выбранным идентификатором {@link Department#id}
     * @param id - идентификатор департамента
     */
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable Long id) throws SQLException {
        try {
            departmentService.deleteById(id);
        } catch (NoResultException e) {
            log.error("NoResultException in deleteDepartmentById", e);
            throw new FileNotFoundException("Такого отдела не существует");
        }
    }

}
