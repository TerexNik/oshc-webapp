package ru.OSHC.service;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import ru.OSHC.entity.Grade;

import java.sql.SQLException;

@Service
public class GradeService extends BaseService<Grade> {
    public void add(Grade grade) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.save("Grade", grade);
        closeTransactionSession();
    }
}
