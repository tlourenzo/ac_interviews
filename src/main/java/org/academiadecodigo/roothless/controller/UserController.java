package org.academiadecodigo.roothless.controller;

import org.academiadecodigo.roothless.auth.Attribute;
import org.academiadecodigo.roothless.model.User;
import org.academiadecodigo.roothless.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.logging.Logger;

/**
 * Created by codecadet on 05/04/17.
 */
@Controller
@SessionAttributes(Attribute.LOGGED_IN_USER)
public class UserController {

    @Autowired private UserService userService;
    Logger log = Logger.getLogger(this.getClass().getSimpleName());

    @RequestMapping(method = RequestMethod.GET, value = "/main")
    public String listUsers(Model model, @Valid @ModelAttribute(Attribute.LOGGED_IN_USER) User user) {

        if(user == null || user.getUsername().isEmpty() || user.getUsername().equals("")){
            return "redirect:/login";
        }

        if(!model.containsAttribute(Attribute.USER)){
            model.addAttribute(Attribute.USER, new User());
        }
        model.addAttribute(Attribute.LOGGED_IN_USER, user);
        model.addAttribute(Attribute.USER_LIST, userService.findAll());
        return "main";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/main/add")
    public String addUser(Model model, @Valid @ModelAttribute("user") User user, RedirectAttributes redirectAttributes, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return Attribute.MAIN_PAGE;
        }
        System.out.println(user);

        if(userService.findByName(user.getUsername())==null){
        userService.addUser(user);
        }else{
            userService.updateUser(user);
        }

        redirectAttributes.addFlashAttribute(Attribute.MESSAGE,
                "Added/Updated user " + user.getUsername() + " successfully!");

        return "redirect:/"+Attribute.MAIN_PAGE;

    }

    @RequestMapping(method = RequestMethod.GET, value = "/main/delete/{username}")
    public String deleteUser(@PathVariable("username") String username, RedirectAttributes redirectAttributes) {

        User user = userService.findByName(username);
        redirectAttributes.addFlashAttribute(Attribute.MESSAGE,
                "Deleted user " + user.getUsername() + " successfully!");
        userService.removeUser(user);
        return "redirect:/"+Attribute.MAIN_PAGE;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/main/edit/{username}")
    public String editUser(Model model, @ModelAttribute("username") String username, RedirectAttributes redirectAttributes, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return Attribute.MAIN_PAGE;
        }
        redirectAttributes.addFlashAttribute(Attribute.USER, userService.findByName(username));
        redirectAttributes.addFlashAttribute(Attribute.MESSAGE,
                "Now editing user: " + username + "!");
        return "redirect:/"+Attribute.MAIN_PAGE;
    }

    @RequestMapping(method = RequestMethod.GET, value="/main/logout")
    public String logout(@ModelAttribute User user, BindingResult bindingResult, SessionStatus status){

        if(bindingResult.hasErrors()){
            return Attribute.MAIN_PAGE;
        }

        status.setComplete();
        return "redirect:/"+Attribute.LOGIN_PAGE;
    }

}
