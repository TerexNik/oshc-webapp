package ru.OSHC.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.OSHC.util.HibernateUtil;

public class InitTabels {
    public static void main(String[] args) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        sf.close();
    }
}
