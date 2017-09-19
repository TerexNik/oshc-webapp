package ru.OSHC.model.service;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import ru.OSHC.model.entity.Certificate;

import java.sql.SQLException;

@Service
public class CertificateService extends BaseService<Certificate> {
    public void add(Certificate certificate) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.save("Certificate", certificate);
        closeTransactionSession();
    }
}



