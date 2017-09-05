package ru.OSHC.service;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import ru.OSHC.entity.Department;
import ru.OSHC.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService extends BaseService<Department> {
    public List<Employee> getEmployeesFromDepartment(Long id) throws SQLException{
        List<Employee> employees = getAll("getEmployeeList");
        List<Employee> response = new ArrayList<Employee>();
        openTransactionSession();
        Session session = getSession();
        for (Employee e : employees) {
            if (e.getDepartmentId().getId() == id) {
                response.add(e);
            }
        }
        closeTransactionSession();
        return employees;
    }
}
