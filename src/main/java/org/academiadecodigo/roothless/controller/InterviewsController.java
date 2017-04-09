package org.academiadecodigo.roothless.controller;

import org.academiadecodigo.roothless.auth.Attribute;
import org.academiadecodigo.roothless.model.Interview;
import org.academiadecodigo.roothless.model.User;
import org.academiadecodigo.roothless.service.Interview.InterviewService;
import org.academiadecodigo.roothless.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by tlourenzo on 08-04-2017.
 */
@Controller
@SessionAttributes(Attribute.LOGGED_IN_USER)
public class InterviewsController {

    @Autowired
    private UserService userService;

    @Autowired
    private InterviewService interviewService;


    @RequestMapping(method = RequestMethod.GET, value = "/interviews")
    public String listUserInterviews(Model model, @Valid @ModelAttribute(Attribute.LOGGED_IN_USER) User user) {

        if(user == null || user.getUsername().isEmpty() || user.getUsername().equals("")){
            return "redirect:/login";
        }

        if(!model.containsAttribute(Attribute.INTERVIEW)){
            model.addAttribute(Attribute.INTERVIEW, new Interview());
        }

        User loggedUser = userService.findByName(user.getUsername());
        model.addAttribute(Attribute.LOGGED_IN_USER, loggedUser);
        model.addAttribute(Attribute.INTERVIEW_LIST, interviewService.getInterviewsByUser(user.getUser_id()));
        return "interviews";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/interview/add")
    public String addInterview(Model model, @Valid @ModelAttribute("interview") Interview interview,@ModelAttribute(Attribute.LOGGED_IN_USER) User user, RedirectAttributes redirectAttributes, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return Attribute.INTERVIEW_PAGE;
        }
        List<Interview> userInterviews = interviewService.getInterviewsByUser(user.getUser_id());

        if (userInterviews == null) {
            interview.setUser_id(user.getUser_id());
            interviewService.addInterview(interview);

        } else {
            Interview currentInterview = interviewService.getInterviewBySeveral(user.getUser_id(),interview.getCompany(),interview.getDate(),interview.getHour());
            for (Interview i : userInterviews) {

                if(currentInterview != null){
                    interview.setInterview_id(currentInterview.getInterview_id());
                    interview.setUser_id(currentInterview.getUser_id());
                    interviewService.updateInterview(interview);
                    redirectAttributes.addFlashAttribute(Attribute.MESSAGE,
                            "Interview Updated successfully");
                    return "redirect:/" + Attribute.INTERVIEW_PAGE;
                }

                if (i.getUser_id()==interview.getUser_id() && i.getDate().equals(interview.getDate()) && i.getHour().equals(interview.getHour())) {
                    redirectAttributes.addFlashAttribute(Attribute.MESSAGE,
                            "User already has one interview at that date/time!");
                    return "redirect:/" + Attribute.INTERVIEW_PAGE;
                }

            }
            interview.setUser_id(user.getUser_id());
            interviewService.addInterview(interview);


        }
        redirectAttributes.addFlashAttribute(Attribute.MESSAGE,
                "Added/Updated interview successfully!");
        return "redirect:/" + Attribute.INTERVIEW_PAGE;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/interview/delete/{interview_id}")
    public String deleteInterview(@PathVariable("interview_id") int interview_id, RedirectAttributes redirectAttributes) {

        Interview interview = interviewService.getInterviewById(interview_id);
        redirectAttributes.addFlashAttribute(Attribute.MESSAGE,
                "Deleted interview successfully!");
        interviewService.removeInterview(interview);
        return "redirect:/"+Attribute.INTERVIEW_PAGE;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/interview/edit/{interview_id}")
    public String editInterview(Model model, @ModelAttribute("interview_id") int interview_id, RedirectAttributes redirectAttributes, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return Attribute.INTERVIEW_PAGE;
        }
        redirectAttributes.addFlashAttribute(Attribute.INTERVIEW, interviewService.getInterviewById(interview_id));
        redirectAttributes.addFlashAttribute(Attribute.MESSAGE,
                "Now editing your Interview Logger!");
        return "redirect:/"+Attribute.INTERVIEW_PAGE;
    }

    @RequestMapping(method = RequestMethod.GET, value="/interview/logout")
    public String logout(@ModelAttribute User user, BindingResult bindingResult, SessionStatus status){

        if(bindingResult.hasErrors()){
            return Attribute.INTERVIEW_PAGE;
        }

        status.setComplete();
        return "redirect:/"+Attribute.LOGIN_PAGE;
    }

}
