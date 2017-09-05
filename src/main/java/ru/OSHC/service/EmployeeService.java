package ru.OSHC.service;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import ru.OSHC.dao.EmployeeDAO;
import ru.OSHC.entity.Department;
import ru.OSHC.entity.Employee;

import java.sql.SQLException;
import java.util.List;

@Service
public class EmployeeService extends BaseService<Employee> implements EmployeeDAO{

    public void migrateFromDepAtoDepB(Department from, Department to) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        List<Employee> employees = getAll("getEmployeeList");
        for (Employee e : employees) {
            if (e.getDepartmentId() == from) {
                e.setDepartmentId(to);
            }
        }
        closeTransactionSession();
    }
}
