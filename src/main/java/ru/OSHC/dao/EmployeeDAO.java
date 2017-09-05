package ru.OSHC.dao;

import ru.OSHC.entity.Department;
import ru.OSHC.entity.Employee;

import java.sql.SQLException;

public interface EmployeeDAO extends BaseDAO<Employee>{
    void migrateFromDepAtoDepB(Department from, Department to) throws SQLException;
}
