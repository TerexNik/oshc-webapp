package ru.OSHC.service;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.OSHC.dao.CertificateDAO;
import ru.OSHC.entity.Certificate;
import ru.OSHC.util.SessionUtill;
import ru.OSHC.util.HqlConstants;

import java.sql.SQLException;
import java.util.List;

@Service
public class CertificateService extends SessionUtill implements CertificateDAO, HqlConstants {
    public Certificate getByID(long id) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        String hql = "from Certificate c where c.id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", (long)id);
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
    @Autowired
    public List<Certificate> getAll() throws SQLException {
        openTransactionSession();
        Session session = getSession();
        Query query = session.createQuery("from Certificate c");
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

    public void remove(long id) throws SQLException{
        Certificate certificate = getByID(id);
        openTransactionSession();
        Session session = getSession();
        session.remove(certificate);
        closeTransactionSession();
    }
}



