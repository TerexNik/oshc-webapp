package ru.OSHC.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.OSHC.entity.Grade;
import ru.OSHC.exception.FileNotFoundException;
import ru.OSHC.service.GradeService;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

/**
 * Контроллер грейда
 */
@RestController
@RequestMapping("/grades")
public class GradeController {

    private static final Logger log = Logger.getLogger(GradeController.class);
    private GradeService gradeService;

    @Autowired
    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    /**
     * Добавление нового грейда.
     *
     * @param grade - грейд
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void addGrade(@RequestBody Grade grade) throws SQLException {
        gradeService.add(grade);
//        log.info("Grade was added");
    }

    /**
     * Изменение данных о грейде
     *
     * @param grade - грейд
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@RequestBody Grade grade) throws SQLException {
        gradeService.update(grade);
//        log.info("Grade was updated");
    }

    /**
     * Получение списка грейдов
     *
     * @return возвращает список всех грейдов
     */
    @RequestMapping(method = RequestMethod.GET)
    List getAll() throws SQLException {
        return gradeService.getAll("getGradesList");
    }

    /**
     * Получение информации о грейде с идентификатором {@link Grade#id}
     *
     * @param id - идентификатор грейда
     * @return возвращает выбранный грейд
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    Grade getGradeById(@PathVariable Long id) throws SQLException {
        try {
            return gradeService.getById(id, "getGradeById");
        } catch (NoResultException e) {
            log.error("NoResultException in getGradeById", e);
            throw new FileNotFoundException("Такого грейда не существует");
        }
    }

    /**
     * Удаление грейда с выбранным идентификатором {@link Grade#id}
     *
     * @param id - идентификатор грейда
     */
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable Long id) throws SQLException {
        try {
            gradeService.removeById(id, "getGradeById");
        } catch (NoResultException e) {
            log.error("NoResultException in removeGradeById", e);
            throw new FileNotFoundException("Такого грейда не существует");
        }
    }

}
