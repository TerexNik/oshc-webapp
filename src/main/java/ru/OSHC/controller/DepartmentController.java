package ru.OSHC.controller;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.OSHC.entity.Department;
import ru.OSHC.service.DepartmentService;

import javax.persistence.NoResultException;
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
    void addDepartment(@RequestBody Department department) {
        try {
            departmentService.add(department);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Изменение данных о департаменте
     * @param department - департамент
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@RequestBody Department department) {
        try {
            departmentService.update(department);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Получение списка департаментов
     * @return возвращает список всех департаментов
     */
    @RequestMapping(method = RequestMethod.GET)
    List getAll(){
        try {
            return departmentService.getAll("getDepartmentList");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Получение списка поддепартаментов
     * @return возвращает список поддепартаментов
     */
    @RequestMapping(value = "/get/{id}/sub-departments", method = RequestMethod.GET)
    List getSubDepartments(@PathVariable long id) {
        try {
            return departmentService.getSubDepartments(id);
        } catch (SQLException e) {
            log.error("getSubDepartments", e);
            return null;
        }
    }

    /**
     * Получение информации о департаменте с идентификатором {@link Department#id}
     * @param id - идентификатор департамента
     * @return возвращает выбранный департамент
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    Department getElementById(@PathVariable Long id) {
        try {
            return departmentService.getById(id, "getDepartmentById");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Удаление департамента с выбранным идентификатором {@link Department#id}
     * @param id - идентификатор департамента
     */
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable Long id) {
        try {
            departmentService.deleteById(id);
        } catch (SQLException e) {
            log.error("deleteById", e);
        } catch (ConstraintViolationException e) {
            log.error("remove CVEx", e);
        } catch (NoResultException e) {
            log.error("remove NREx", e);
        }
    }

}
