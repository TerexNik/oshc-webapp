package ru.OSHC.model.service;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.OSHC.model.dao.BaseDAO;
import ru.OSHC.util.SessionUtil;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseService<T> extends SessionUtil implements BaseDAO<T> {

    public T getById(Long id, String namedQuerry) throws SQLException, NoResultException {
        openTransactionSession();
        Session session = getSession();
        Query query = session.createNamedQuery(namedQuerry);
        query.setParameter("id",(long) id);
        T obj = (T) query.getSingleResult();
        closeTransactionSession();
        return obj;
    }

    public void removeById(Long id, String namedQuerry) throws SQLException {
        T obj = getById(id, namedQuerry);
        openTransactionSession();
        Session session = getSession();
        session.remove(obj);
        closeTransactionSession();
    }

    public List getAll(String namedHQL) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        Query query = session.createNamedQuery(namedHQL);
        List list = query.list();
        closeTransactionSession();
        return list;
    }

    public void add(T obj) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.save(obj);
        closeTransactionSession();
    }

    public void update(T obj) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.update(obj);
        closeTransactionSession();
    }

    public void remove(T obj) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.persist(obj);
        closeTransactionSession();
    }

    protected boolean hasDuplicates(long id, String namedQuerry) throws SQLException {
        try {
            return getById(id, namedQuerry) != null;
        } catch (NoResultException e) {
            return false;
        }
    }
}
