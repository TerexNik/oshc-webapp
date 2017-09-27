package ru.OSHC.webService;

import ru.OSHC.service.GradeService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.sql.SQLException;
import java.util.List;


@WebService(serviceName = "/grades")
public class GradeWebService {
    private GradeService gradeService;

    @WebMethod(operationName = "getGrades")
    public List getGrades() throws SQLException {
        return gradeService.getAll("getGradesList");
    }

    public void setGradeService(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    public GradeService getGradeService() {
        return gradeService;
    }
}
