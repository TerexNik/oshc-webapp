package ru.OSHC.service;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import ru.OSHC.entity.Certificate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CertificateService extends BaseService<Certificate> {
    public void add(Certificate certificate) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.save("Certificate", certificate);
        closeTransactionSession();
    }

}



