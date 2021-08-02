package com.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable e) {
            System.out.println("Caught Exception : " + e + "!!!");
            throw new ExceptionInInitializerError();
        }
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
