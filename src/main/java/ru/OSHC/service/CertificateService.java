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


    public List getCertificates(long id) throws SQLException {
        List<Certificate> certificates = getAll("getCertificateList");
        List<Certificate> result = new ArrayList<Certificate>();
        for (Certificate d: certificates) {
            if (d.getParentDepartment() == searchById(id, departments)) {
                result.add(d);
            }
        }
        return result;
    }

    public void deleteById(long id) throws SQLException{
        List<Certificate> certificates = getSubDepartments(id);
        Department department = getById(id, "getDepartmentById");
        openTransactionSession();
        Session session = getSession();
        for (Department d : subDepartments) {
            d.setParentDepartment(null);
            session.update(d);
        }
        session.remove(department);
        closeTransactionSession();
    }
}



