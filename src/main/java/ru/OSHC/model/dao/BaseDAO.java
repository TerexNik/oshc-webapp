package ru.OSHC.model.dao;

import java.sql.SQLException;
import java.util.List;

public interface BaseDAO<T> {
    Object getById(Long id, String namedQuery) throws SQLException;

    void removeById(Long id, String namedQuery) throws SQLException;

    void add(T obj) throws SQLException;

    List getAll(String namedHQL) throws SQLException;

    void update(T obj) throws SQLException;

    void remove(T obj) throws SQLException;
}
