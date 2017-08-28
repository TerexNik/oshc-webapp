package ru.OSHC.dao;

import ru.OSHC.entity.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO {
    Employee getById(int id) throws SQLException;

    void add(Employee employee) throws SQLException;

    List<Employee> getAll() throws SQLException;

    void update(Employee employee) throws SQLException;

    void remove(int id) throws SQLException;
}
