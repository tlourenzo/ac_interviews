package org.academiadecodigo.roothless.model;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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

    @Pattern(regexp = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$", message = "Date is not valid!")
    private Calendar date;


    @Pattern(regexp = "([01]?[0-9]|2[0-3]):[0-5][0-9]", message = "Hour is not valid!")
    @Size(min = 5, max = 5)
    private String hour;

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

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    @Override
    public String toString() {
        return "Interview{" +
                "interview_id=" + interview_id +
                ", user_id=" + user_id +
                ", date=" + date +
                ", hour=" + hour +
                ", company='" + company + '\'' +
                ", interviewer='" + interviewer + '\'' +
                ", interviewType='" + interviewType + '\'' +
                ", comments='" + comments + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Interview interview = (Interview) o;

        if (user_id != interview.user_id) return false;
        if (!date.equals(interview.date)) return false;
        return hour.equals(interview.hour);
    }

    @Override
    public int hashCode() {
        int result = user_id;
        result = 31 * result + date.hashCode();
        result = 31 * result + hour.hashCode();
        return result;
    }
}
