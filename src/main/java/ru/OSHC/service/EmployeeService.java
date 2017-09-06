package ru.OSHC.service;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import ru.OSHC.dao.EmployeeDAO;
import ru.OSHC.entity.Department;
import ru.OSHC.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService extends BaseService<Employee> implements EmployeeDAO{

    public void migrateFromDepAtoDepB(Department from, Department to) throws SQLException {
        List<Employee> employees = getAll("getEmployeeList");
        openTransactionSession();
        Session session = getSession();
        for (Employee e : employees) {
            if (e.getDepartment().getId() == from.getId()) {
                e.setDepartment(to);
                session.update(e);
            }
        }
        closeTransactionSession();
    }

    public List<Employee> getEmployeesFromDepartment(Long id, List<Employee> employees) throws SQLException{
        List<Employee> result = new ArrayList<Employee>();
        for (Employee e: employees) {
            if (e.getDepartment().getId() == id) {
                result.add(e);
            }
        }
        return result;
    }
}
