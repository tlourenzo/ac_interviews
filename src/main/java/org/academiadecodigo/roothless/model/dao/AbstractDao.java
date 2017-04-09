package org.academiadecodigo.roothless.model.dao;

import org.academiadecodigo.roothless.persistence.HibernateSessionManager;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by codecadet on 24/03/17.
 */
@Repository
@Transactional
public abstract class AbstractDao<T> implements DAO<T> {

    @Autowired private HibernateSessionManager hibernateSessionManager;
    private Session session;
    private Class<T> type;


    public HibernateSessionManager getHibernateSessionManager() {
        return hibernateSessionManager;
    }

    public AbstractDao() {
    }

    public AbstractDao(Class<T> type) {
        this.type = type;
    }

    @Override
    public void create(T t) {
        try{
            session = hibernateSessionManager.getSession();
            System.out.println(t.toString());
            session.saveOrUpdate(t);
        }catch(HibernateException hex){
            session.clear();
            System.out.println(hex.getMessage());
        }
    }

    @Override
    public T read(String tID) {
        try{
            session = hibernateSessionManager.getSession();
            return session.get(type,tID);
        }catch(HibernateException hex){
            System.out.println(hex.getMessage());
        }
        return null;
    }

    @Override
    public void update(T t) {
        try{
            session = hibernateSessionManager.getSession();
            session.merge(t);
        }catch(HibernateException hex){
            System.out.println(hex.getMessage());
        }
    }

    @Override
    public void delete(T t) {
        try{
            session = hibernateSessionManager.getSession();
            session.delete(t);
        }catch(HibernateException hex){
            System.out.println(hex.getMessage());
        }
    }

    @Override
    public List<T> listAll() {
        List<T> list = null;
        try{
            session = hibernateSessionManager.getSession();
            list = session.createCriteria(type).list();
        }catch(HibernateException hex){
            System.out.println(hex.getMessage());
        }

        return list;
    }
}
