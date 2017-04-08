package org.academiadecodigo.roothless.model;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

/**
 * Created by tlourenzo on 08-04-2017.
 */
@Component
public class Interview {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "interview_id")
    private int interview_id;

    @NotNull
    @NotBlank
    private int user_id;

    private Calendar date;
    private int hour;

    @NotNull(message = "Company is mandatory")
    @NotBlank(message = "Company is mandatory")
    private String company;

    private String interviewer;

    @NotNull(message = "Interview Type is mandatory")
    @NotBlank(message = "Interview Type  is mandatory")
    private String interviewType;

    private String comments;

    @NotNull(message = "Status is mandatory")
    @NotBlank(message = "Status is mandatory")
    private String status;

    public Interview() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(String interviewer) {
        this.interviewer = interviewer;
    }

    public String getInterviewType() {
        return interviewType;
    }

    public void setInterviewType(String interviewType) {
        this.interviewType = interviewType;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getInterview_id() {
        return interview_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public void setInterview_id(int interview_id) {
        this.interview_id = interview_id;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }
}
