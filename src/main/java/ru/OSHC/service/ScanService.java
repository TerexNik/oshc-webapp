package ru.OSHC.service;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import ru.OSHC.entity.Scan;

import javax.persistence.NoResultException;
import java.sql.SQLException;

@Service
public class ScanService extends BaseService<Scan> {
    public void add(Scan scan) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.save("Certificate", scan);
        closeTransactionSession();
    }

    public Scan getById(Long id) throws SQLException, NoResultException {
        openTransactionSession();
        Session session = getSession();
        Scan scan = session.load(Scan.class, id);
        closeTransactionSession();
        return scan;
    }
}
