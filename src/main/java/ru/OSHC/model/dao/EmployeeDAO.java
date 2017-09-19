package ru.OSHC.model.dao;

import ru.OSHC.model.entity.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO extends BaseDAO<Employee>{
    void migrateFromDepAtoDepB(long from, long to) throws SQLException;
    List getEmployeesFromDepartment(Long id) throws SQLException;
}
