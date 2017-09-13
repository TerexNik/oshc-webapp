package ru.OSHC.service;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import ru.OSHC.dao.EmployeeDAO;
import ru.OSHC.entity.Department;
import ru.OSHC.entity.Employee;
import ru.OSHC.entity.Grade;
import ru.OSHC.entity.Post;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService extends BaseService<Employee> implements EmployeeDAO {

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

    public void migrateToNewGrade(Grade newGrade, Employee employee) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        employee.setGrade(newGrade);
        session.update(employee);
        closeTransactionSession();
    }

    public void migrateToNewPost(Post newPost, Employee employee) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        employee.setPost(newPost);
        session.update(employee);
        closeTransactionSession();
    }

    public List<Employee> getEmployeesFromDepartment(Long id, List<Employee> employees) throws SQLException {
        List<Employee> result = new ArrayList<Employee>();
        for (Employee e : employees) {
            if (e.getDepartment().getId() == id) {
                result.add(e);
            }
        }
        return result;
    }

    @Override
    public void add(Employee employee) throws SQLException {
        employee.setStartDate(new Date(System.currentTimeMillis()));
        employee.setActive(true);
        super.add(employee);
    }

    @Override
    public void update(Employee employee) throws SQLException {
        Employee employeeHist = getById(employee.getHistoryId(), "getEmployeeById");
        employeeHist.setActive(false);
        employeeHist.setEndDate(new Date(System.currentTimeMillis()));
        super.update(employeeHist);
        add(employee);
    }

    @Override
    public void remove(Employee employee) throws SQLException {
        employee.setActive(false);
        employee.setEndDate(new Date(System.currentTimeMillis()));
        super.update(employee);
    }

    public void removeById(Long id) throws SQLException {
        remove(getById(id, "getEmployeeById"));
    }
}
