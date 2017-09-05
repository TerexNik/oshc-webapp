package ru.OSHC.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.OSHC.entity.Grade;
import ru.OSHC.service.GradeService;

import java.util.List;

@RestController
@RequestMapping("/grades")
public class GradeController {

    private static final Logger log = Logger.getLogger(GradeController.class);
    private BaseCRUDController<Grade> baseCRUDController;

    @Autowired
    public GradeController(GradeService gradeService) {
        baseCRUDController = new BaseCRUDController<Grade>(gradeService);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void addGrade(@RequestBody Grade grade) {
        baseCRUDController.add(grade);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@RequestBody Grade grade) {
        baseCRUDController.update(grade);
    }

    @RequestMapping(value = "/getClear", method = RequestMethod.GET)
    List getWithNames() {
        return baseCRUDController.getList("getGradesWithNames");
    }

    @RequestMapping(method = RequestMethod.GET)
    List getAll() {
        return baseCRUDController.getList("getGradesList");
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    Grade getGradeById(@PathVariable Long id) {
        return baseCRUDController.getById(id, "getGradeById");
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable Long id) {
        baseCRUDController.deleteById(id, "getGradeById");
    }

}
