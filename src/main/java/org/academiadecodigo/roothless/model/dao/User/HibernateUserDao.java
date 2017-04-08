package org.academiadecodigo.roothless.model.dao.User;

import org.academiadecodigo.roothless.model.User;
import org.academiadecodigo.roothless.model.dao.AbstractDao;
import org.academiadecodigo.roothless.model.dao.User.UserDao;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by codecadet on 23/03/17.
 */
@Repository
@Transactional
public class HibernateUserDao extends AbstractDao<User> implements UserDao {

    public HibernateUserDao() {
        super(User.class);
    }

    @Override
    public User getUser(String user) {
        User u = null;
        try{
            Session session = super.getHibernateSessionManager().getSession();
            Query query = session.createQuery("from User where username = :username ");
            query.setString("username", user);
            u = (User) query.uniqueResult();

        }catch(HibernateException hex){
            System.out.println(hex.getMessage());
        }finally {
            //super.getHibernateSessionManager().close();
        }
        return u;
    }

    @Override
    public User getUserByID(int user_id) {
        User u = null;
        try{
            Session session = super.getHibernateSessionManager().getSession();
            Query query = session.createQuery("from User where user_id = :user_id ");
            String u_id= ""+user_id;
            query.setString("user_id", u_id);
            u = (User) query.uniqueResult();

        }catch(HibernateException hex){
            System.out.println(hex.getMessage());
        }finally {
            //super.getHibernateSessionManager().close();
        }
        return u;
    }
}
