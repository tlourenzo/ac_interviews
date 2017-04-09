package org.academiadecodigo.roothless.model.dao.Interview;

import org.academiadecodigo.roothless.model.Interview;

import org.academiadecodigo.roothless.model.dao.AbstractDao;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by tlourenzo on 08-04-2017.
 */
@Repository
@Transactional
public class HibernateInterviewDAO extends AbstractDao<Interview> implements InterviewDao {

    Logger log = Logger.getLogger(this.getClass().getSimpleName());

    public HibernateInterviewDAO() {
        super(Interview.class);
    }

    @Override
    public Interview getInterviewByID(int interview_id) {
        Interview i = null;
        try{
            Session session = super.getHibernateSessionManager().getSession();
            Query query = session.createQuery("from Interview where interview_id = :interview_id");
            query.setString("interview_id", ""+interview_id);
            i = (Interview) query.uniqueResult();
        }catch(HibernateException hex){
            System.out.println(hex.getMessage());
        }finally {
            //super.getHibernateSessionManager().close();
        }
        return i;
    }

    @Override
    public Interview getInterviewBySeveral(int user_id, String company, String date, String hour) {
        Interview interview = null;
        try{
            Session session = super.getHibernateSessionManager().getSession();
            interview = (Interview) session.createCriteria(Interview.class)
                    .add(Restrictions.eq("user_id",user_id))
                    .add(Restrictions.eq("company",company))
                    .add(Restrictions.eq("date",date))
                    .add(Restrictions.eq("hour",hour)).uniqueResult();
        }catch(HibernateException hex){
            System.out.println(hex.getMessage());
        }
        return interview;
    }

    @Override
    public Interview getInterviewByUserAndCompany(int user_id, String company) {
        return null;
    }

    @Override
    public List<Interview> getAllInterviewsByUser(int user_id) {
        List<Interview> list = null;
        try{
            Session session = super.getHibernateSessionManager().getSession();
            list =  session.createCriteria(Interview.class)
                    .add(Restrictions.eq("user_id",user_id))
                    .addOrder(Order.asc("date"))
                    .list();
        }catch(HibernateException hex){
            System.out.println(hex.getMessage());
        }
        return list;
    }
}
