package org.academiadecodigo.roothless.controller;

import org.academiadecodigo.roothless.auth.Attribute;
import org.academiadecodigo.roothless.model.User;
import org.academiadecodigo.roothless.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by tlourenzo on 08-04-2017.
 */
@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/register/new")
    public String registerUser(Model model, @Valid @ModelAttribute(Attribute.USER) User user) {

        if(user == null || user.getUsername().isEmpty() || user.getUsername().equals("")){
            return "redirect:/login";
        }
        model.addAttribute(Attribute.USER, new User());

        return Attribute.REGISTER_PAGE;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public String addNewUser(@Valid @ModelAttribute("user") User user, RedirectAttributes redirectAttributes, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return Attribute.REGISTER_PAGE;
        }
        System.out.println(user);

        if(userService.findByName(user.getUsername())==null){
            userService.addUser(user);
        }else{
            userService.updateUser(user);
        }

        return "redirect:/"+Attribute.LOGIN_PAGE;

    }

}
