package ru.OSHC.service;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import ru.OSHC.dao.EmployeeDAO;
import ru.OSHC.entity.Employee;
import ru.OSHC.util.SessionUtill;

import java.sql.SQLException;
import java.util.List;

@Service
public class EmployeeService extends SessionUtill implements EmployeeDAO{
    public Employee getById(int id) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        String sqlString = "SELECT * FROM EMPLOYEE WHERE ID = :id";
        Query query = session.createNativeQuery(sqlString).addEntity(Employee.class);
        query.setParameter("id",(long) id);
        Employee employee = (Employee) query.getSingleResult();
        closeTransactionSession();
        return employee;
    }

    public void add(Employee employee) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.persist(employee);
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

    public void remove(int id) throws SQLException {
        Employee employee = getById(id);
        openTransactionSession();
        Session session = getSession();
        session.remove(employee);
        closeTransactionSession();
    }
}
