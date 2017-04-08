package org.academiadecodigo.roothless.model.dao.Interview;

import org.academiadecodigo.roothless.model.Interview;

import org.academiadecodigo.roothless.model.dao.AbstractDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tlourenzo on 08-04-2017.
 */
@Repository
@Transactional
public class HibernateInterviewDAO extends AbstractDao<Interview> implements InterviewDao {

    public HibernateInterviewDAO() {
        super(Interview.class);
    }

    @Override
    public Interview getInterviewByID(int interview_id) {
        return null;
    }

    @Override
    public Interview getInterviewByUserAndCompany(int user_id, String company) {
        return null;
    }

    @Override
    public List<Interview> getAllInterviewsByUser(int user_id) {
        return null;
    }
}
