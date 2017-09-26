package ru.OSHC.webService;

import ru.OSHC.entity.Department;
import ru.OSHC.service.DepartmentService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.sql.SQLException;
import java.util.List;

@WebService(serviceName = "/departments")
public class DepartmentWebService {
    private DepartmentService departmentService;

    @WebMethod(operationName = "getDeps")
    public List getSubDepartments() throws SQLException {
        return departmentService.getAll("getDepartmentList");
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public DepartmentService getDepartmentService() {
        return departmentService;
    }
}
