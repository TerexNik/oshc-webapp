package ru.OSHC.service;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import ru.OSHC.entity.Department;

import java.sql.SQLException;

@Service
public class DepartmentService extends BaseService<Department> {
    public void add(Department department) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.save("Department", department);
        closeTransactionSession();
    }
}
