package ru.OSHC.service;

import org.hibernate.Session;
import org.hibernate.query.Query;
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

    public void employeeMigrateFromDepAtoDepB(long id, Department from, Department to) throws SQLException {
        Employee employee = getById(id,"getEmployeeById");
        openTransactionSession();
        Session session = getSession();
            if (employee.getDepartment().getId() == from.getId()) {
                employee.setDepartment(to);
                session.update(employee);
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

    public Employee getActiveEmployeeById(Long id, List<Employee> employees) throws SQLException {
        Employee employee = new Employee();
        for (Employee e : employees) {
            if (e.isActive() == true) {
                employee = e;
            }
        }
        return employee;
    }
}
