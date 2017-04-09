package org.academiadecodigo.roothless.model.dao.Interview;

import org.academiadecodigo.roothless.model.Interview;
import org.academiadecodigo.roothless.model.dao.DAO;

import java.util.List;

/**
 * Created by tlourenzo on 08-04-2017.
 */
public interface InterviewDao extends DAO<Interview> {
    Interview getInterviewByID(int interview_id);
    Interview getInterviewBySeveral(int user_id, String company, String date, String hour);
    Interview getInterviewByUserAndCompany(int user_id, String company);
    List<Interview> getAllInterviewsByUser(int user_id);
}
