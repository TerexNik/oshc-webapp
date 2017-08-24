package ru.OSHC.dao;

import ru.OSHC.entity.Certificate;

import java.sql.SQLException;
import java.util.List;

public interface CertificateDAO {
    Certificate getByID(int id) throws SQLException;

    void add(Certificate certificate) throws SQLException;

    List<Certificate> getAll() throws SQLException;

    void update(Certificate certificate) throws SQLException;

    void remove(Certificate certificate) throws SQLException;
}
