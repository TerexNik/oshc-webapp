package ru.OSHC.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.OSHC.entity.Grade;
import ru.OSHC.service.GradeService;

import java.util.List;

/**
 * Контроллер грейда
 */
@RestController
@RequestMapping("/grades")
public class GradeController {

    private static final Logger log = Logger.getLogger(GradeController.class);
    private BaseCRUDController<Grade> baseCRUDController;

    @Autowired
    public GradeController(GradeService gradeService) {
        baseCRUDController = new BaseCRUDController<Grade>(gradeService);
    }

    /**
     * Добавление нового грейда.
     * @param grade - грейд
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void addGrade(@RequestBody Grade grade) {
        baseCRUDController.add(grade);
    }

    /**
     * Изменение данных о грейде
     * @param grade - грейд
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@RequestBody Grade grade) {
        baseCRUDController.update(grade);
    }

    /**
     * Получение списка грейдов
     * @return возвращает список всех грейдов
     */
    @RequestMapping(method = RequestMethod.GET)
    List getAll() {
        return baseCRUDController.getList("getGradesList");
    }

    /**
     * Получение информации о грейде с идентификатором {@link Grade#id}
     * @param id - идентификатор грейда
     * @return возвращает выбранный грейд
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    Grade getGradeById(@PathVariable Long id) {
        return baseCRUDController.getById(id, "getGradeById");
    }

    /**
     * Удаление грейда с выбранным идентификатором {@link Grade#id}
     * @param id - идентификатор грейда
     */
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable Long id) {
        baseCRUDController.deleteById(id, "getGradeById");
    }

}
