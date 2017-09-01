package ru.OSHC.dao;

import java.sql.SQLException;
import java.util.List;

public interface BaseDAO<T> {
    Object getById(Long id, String namedQuery) throws SQLException;

    void removeById(Long id, String namedQuery) throws SQLException;

    List getWithNames(String namedQuery) throws SQLException;

    void add(T obj) throws SQLException;

    List getAll(String className) throws SQLException;

    void update(T obj) throws SQLException;

    void remove(T obj) throws SQLException;
}
