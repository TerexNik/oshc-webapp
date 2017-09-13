package ru.OSHC.service;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import ru.OSHC.dao.EmployeeDAO;
import ru.OSHC.entity.Department;
import ru.OSHC.entity.Employee;
import ru.OSHC.entity.Grade;
import ru.OSHC.entity.Post;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@Service
public class EmployeeService extends BaseService<Employee> implements EmployeeDAO {

    public void migrateFromDepAtoDepB(long fromId, long toId) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        Department to = session.get(Department.class, toId);
        closeTransactionSession();
        List<Employee> employees = getAll("getActiveEmployee");
        for (Employee e : employees) {
            if (e.getDepartment().getId() == fromId) {
                e.setDepartment(to);
                update(e);
            }
        }
    }

    public List getEmployeesFromDepartment(Long id) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        Query query = session.createNamedQuery("getEmployeesDepartmentId");
        query.setParameter("id", id);
        List employees = query.getResultList();
        closeTransactionSession();
        return employees;
    }

    public void clearDepHistory(long id) throws SQLException{
        List<Employee> employees = getInactive();
        openTransactionSession();
        Session session = getSession();
        for (Employee e : employees) {
            if (e.getDepartment().getId() == id) {
                e.setDepartment(null);
                session.update(e);
            }
        }
        closeTransactionSession();
    }

    public List getInactive() {
        openTransactionSession();
        Session session = getSession();
        Query query = session.createNamedQuery("getInactiveEmployee");
        List employees = query.list();
        closeTransactionSession();
        return employees;
    }

    @Override
    public void add(Employee employee) throws SQLException {
        if (hasDuplicates(employee.getHistoryId(), "getEmployeeById")) {
            setEndDateForEmployee(employee.getHistoryId());
        }
        employee.setStartDate(new Date(System.currentTimeMillis()));
        employee.setActive(true);
        super.add(employee);
    }



    @Override
    public void update(Employee employee) throws SQLException {
        Employee employeeHist = setEndDateForEmployee(employee.getHistoryId());
        super.update(employeeHist);
        add(employee);
    }

    public void migrateToNewGrade(long id, Grade newGrade) throws SQLException {
        Employee employee = setEndDateForEmployee(id);
        employee.setGrade(newGrade);
        add(employee);
    }

    public void migrateToNewPost(long id, Post post) throws SQLException {
        Employee employee = setEndDateForEmployee(id);
        employee.setPost(post);
        add(employee);
    }

    public Employee setEndDateForEmployee(long id) throws SQLException {
        Employee employeeHist = getById(id, "getEmployeeById");
        employeeHist.setActive(false);
        employeeHist.setEndDate(new Date(System.currentTimeMillis()));
        super.update(employeeHist);
        return employeeHist;
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

    public List<Employee> getEmployeeByLetters (String letters, List<Employee> employees) {
        List<Employee> result = new ArrayList<Employee>();
        for (Employee e: employees) {
            if (e.getName().contains(letters) || e.getSurname().contains(letters)
                    || (e.getPatronymic() != null && e.getPatronymic().contains(letters))) {
                result.add(e);
            }
        }
        return result;
    }
}
