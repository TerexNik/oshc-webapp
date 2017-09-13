package ru.OSHC.service;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.OSHC.entity.Department;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService extends BaseService<Department> {

    private final EmployeeService employeeService;

    @Autowired
    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public List getSubDepartments(long id) throws SQLException {
        List<Department> departments = getAll("getDepartmentList");
        List<Department> result = new ArrayList<Department>();
        for (Department d: departments) {
            if (d.getParentDepartment() == searchById(id, departments)) {
                result.add(d);
            }
        }
        return result;
    }

    public void deleteById(long id) throws SQLException{
        List<Department> subDepartments = getSubDepartments(id);
        Department department = getById(id);
        employeeService.clearDepHistory(id);
        openTransactionSession();
        Session session = getSession();
        for (Department d : subDepartments) {
            d.setParentDepartment(null);
            session.update(d);
        }
        session.remove(department);
        closeTransactionSession();
    }

    public Department searchById(long id, List<Department> departments) {
        for (Department d: departments) {
            if (d.getId() == id) return d;
        }
        return null;
    }

    public Department getById(Long id) throws SQLException, NoResultException {
        openTransactionSession();
        Session session = getSession();
        Department department = session.load(Department.class, id);
        closeTransactionSession();
        return department;
    }
}
