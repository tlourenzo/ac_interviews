package org.academiadecodigo.roothless.service.Interview;

import org.academiadecodigo.roothless.model.Interview;
import org.academiadecodigo.roothless.model.dao.Interview.InterviewDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tlourenzo on 08-04-2017.
 */
@Service
public class InterviewServiceImplementation implements InterviewService{

    @Autowired private InterviewDao interviewDao;

    @Override
    public boolean addInterview(Interview interview) {

        if(interviewDao.getInterviewByUserAndCompany(interview.getUser_id(), interview.getCompany())== null){
            interviewDao.create(interview);
            return true;
        }
        return false;
    }

    @Override
    public void removeInterview(Interview interview) {
        interviewDao.delete(interview);
    }

    @Override
    public Interview getInterviewByDate(int day, int month, int year) {
        //TODO verify if there is necessity for this method
        return null;
    }

    @Override
    public Interview getInterviewBySeveral(int user_id, String company, String date, String hour) {
        return interviewDao.getInterviewBySeveral(user_id, company, date, hour);
    }

    @Override
    public Interview getInterviewById(int interview_id) {
        return interviewDao.getInterviewByID(interview_id);
    }

    @Override
    public List<Interview> getInterviewsByUser(int user_id) {
        return interviewDao.getAllInterviewsByUser(user_id);
    }

    @Override
    public int count(int user_id) {
        return interviewDao.getAllInterviewsByUser(user_id).size();
    }

    @Override
    public void updateInterview(Interview interview) {
        System.out.println("Updating"+interview.toString());
        interviewDao.update(interview);
    }

    @Override
    public String getName() {
        return InterviewService.class.getSimpleName();
    }
}
