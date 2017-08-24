package ru.service;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import ru.dao.EmployeeDAO;
import ru.entity.Employee;
import ru.util.SessionUtill;

import java.sql.SQLException;
import java.util.List;

@Service
public class EmployeeService extends SessionUtill implements EmployeeDAO{
    public Employee getById(int id) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        Query query = session.createNativeQuery("select * from EMPLOYEE where id =" + id).addEntity(Employee.class);
        Employee employee = (Employee) query.getSingleResult();
        closeTransactionSession();
        return employee;
    }

    public void add(Employee employee) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.save(employee);
        closeTransactionSession();
    }

    public List<Employee> getAll() throws SQLException {
        openTransactionSession();
        Session session = getSession();
        Query query = session.createNativeQuery("select * from EMPLOYEE").addEntity(Employee.class);
        List<Employee> employees = query.list();
        closeTransactionSession();
        return employees;
    }

    public void update(Employee employee) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.update(employee);
        closeTransactionSession();
    }

    public void remove(Employee employee) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.remove(employee);
        closeTransactionSession();
    }
}
