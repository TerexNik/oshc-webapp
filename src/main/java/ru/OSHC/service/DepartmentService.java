package ru.OSHC.service;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import ru.OSHC.entity.Department;
import ru.OSHC.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService extends BaseService<Department> {
    public List<Employee> getEmployeesFromDepartment(Long id) throws SQLException{
        openTransactionSession();
        Session session = getSession();
        Query query = session.createNamedQuery("getEmployeesDepartmentId");
        query.setParameter("id", id);
        List<Employee> employees = query.list();
        closeTransactionSession();
        return employees;
    }

    public List getSubDepartments(long id) {
        openTransactionSession();
        Session session = getSession();
        Query query = session.createNamedQuery("getSubDepartmentsList");
        query.setParameter("id", id);
        List<Department> departments = query.list();
        closeTransactionSession();
        return departments;
    }
}
