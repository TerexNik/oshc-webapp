package ru.OSHC.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.OSHC.entity.Grade;
import ru.OSHC.service.GradeService;

import java.util.List;

@RestController
@RequestMapping("/grades")
public class GradeController extends BaseCRUDController<Grade> {

    private static final Logger log = Logger.getLogger(GradeController.class);

    @Autowired
    public GradeController(GradeService gradeService) {
        setService(gradeService);
    }

    @RequestMapping(value = "/getClear", method = RequestMethod.GET)
    List getWithNames() {
        return getList("getGradesWithNames");
    }

    @RequestMapping(method = RequestMethod.GET)
    List getAll() {
        return getList("getGradesList");
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    Grade getGradeById(@PathVariable Long id) {
        return getById(id, "getGradeById");
    }

//    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    void deleteById(@PathVariable Long id) {
//        deleteById(id, "getGradeById");
//    }

}
