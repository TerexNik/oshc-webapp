package ru.OSHC.service;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import ru.OSHC.dao.CertificateDAO;
import ru.OSHC.entity.Certificate;
import ru.OSHC.util.SessionUtill;

import java.sql.SQLException;
import java.util.List;

@Service
public class CertificateService extends SessionUtill implements CertificateDAO {
    public Certificate getByID(int id) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        Query query = session.createNativeQuery("select * from CERTIFICATE where id =" + id).addEntity(Certificate.class);
        Certificate certificate = (Certificate) query.getSingleResult();
        closeTransactionSession();
        return certificate;
    }

    public void add(Certificate certificate) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.save(certificate);
        closeTransactionSession();
    }

    public List<Certificate> getAll() throws SQLException {
        openTransactionSession();
        Session session = getSession();
        Query query = session.createNativeQuery("select * from CERTIFICATE").addEntity(Certificate.class);
        List<Certificate> certificates = query.list();
        closeTransactionSession();
        return certificates;
    }

    public void update(Certificate certificate) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.update(certificate);
        closeTransactionSession();
    }

    public void remove(Certificate certificate) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.remove(certificate);
        closeTransactionSession();
    }
}


