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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
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
        model.addAttribute(Attribute.LOGGED_IN_USER, user);

        model.addAttribute(Attribute.INTERVIEW_LIST, interviewService.getInterviewsByUser(user.getUser_id()));
        return "interviews";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/interview/add")
    public String addInterview(Model model, @Valid @ModelAttribute("interview") Interview interview,@ModelAttribute(Attribute.LOGGED_IN_USER) User user, RedirectAttributes redirectAttributes, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return Attribute.INTERVIEW;
        }

        if (interviewService.getInterviewsByUser(user.getUser_id()) == null) {
            interview.setUser_id(user.getUser_id());
            interviewService.addInterview(interview);

        } else {

            List<Interview> userInterviews = interviewService.getInterviewsByUser(user.getUser_id());
            for (Interview i : userInterviews) {
                if (interview.equals(i)) {
                    redirectAttributes.addFlashAttribute(Attribute.MESSAGE,
                            "User already have one interview at that date/time!");
                    return "redirect:/" + Attribute.INTERVIEW;
                }
            }

            interview.setUser_id(user.getUser_id());
            interviewService.addInterview(interview);


        }
        redirectAttributes.addFlashAttribute(Attribute.MESSAGE,
                "Added/Updated interview successfully!");
        return "redirect:/" + Attribute.INTERVIEW;
    }







}
