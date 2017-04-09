package org.academiadecodigo.roothless.service.Interview;

import org.academiadecodigo.roothless.model.Interview;
import org.academiadecodigo.roothless.service.Service;

import java.util.List;

/**
 * Created by tlourenzo on 08-04-2017.
 */
public interface InterviewService extends Service {
    boolean addInterview(Interview interview);
    void removeInterview(Interview interview);
    Interview getInterviewByDate(int day, int month, int year);
    List<Interview> getInterviewsByUser(int user_id);
    int count(int user_id);
    void updateInterview(Interview interview);

}
