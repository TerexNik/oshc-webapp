package ru.utill;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtill {
    private static final SessionFactory sesionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.out.println("Initial session failed" + ex);
            shutdown();
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSesionFactory() {
        return sesionFactory;
    }

    public static void shutdown() {
        getSesionFactory().close();
    }
}
