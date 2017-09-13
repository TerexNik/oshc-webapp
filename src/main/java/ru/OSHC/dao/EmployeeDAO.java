package ru.OSHC.dao;

import ru.OSHC.entity.Department;
import ru.OSHC.entity.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO extends BaseDAO<Employee>{
    void migrateFromDepAtoDepB(long from, long to) throws SQLException;
    List getEmployeesFromDepartment(Long id) throws SQLException;
}
