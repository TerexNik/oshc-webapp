package ru.OSHC.service;

import org.springframework.stereotype.Service;
import ru.OSHC.entity.Department;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService extends BaseService<Department> {


    public List getSubDepartments(long id, List<Department> departments) throws SQLException {
        List<Department> result = new ArrayList<Department>();
        for (Department d: departments) {
            if (d.getParentDepartment() == departments.get((int) id)) {
                result.add(d);
            }
        }
        return result;
    }
}
