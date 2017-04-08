package org.academiadecodigo.roothless.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by codecadet on 20/03/17.
 */
@Component
public class HibernateSessionManager {

    private SessionFactory sessionFactory;

    private HibernateSessionManager() {
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void close() {
        sessionFactory.close();
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


}

