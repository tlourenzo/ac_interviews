package org.academiadecodigo.roothless.controller;

import org.academiadecodigo.roothless.auth.Attribute;
import org.academiadecodigo.roothless.auth.Authenticator;
import org.academiadecodigo.roothless.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by codecadet on 04/04/17.
 */
@Controller
@SessionAttributes(Attribute.LOGGED_IN_USER)
public class LoginController {

    @Autowired private Authenticator authenticator;
    Logger log = Logger.getLogger(this.getClass().getSimpleName());

    @RequestMapping(method = RequestMethod.GET, value = "/login" )
    public ModelAndView showLogin() {

        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject(Attribute.LOGGED_IN_USER, "");
        modelAndView.addObject(Attribute.USER, new User());
        return modelAndView;

    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public String doLogin(Model model, @Valid @ModelAttribute(Attribute.USER) User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "login";
        }

        // If user/pass fields are empty, display the same view again
        if (user.getUsername() == null || user.getUsername().isEmpty() || user.getPassword() == null || user.getPassword().isEmpty()) {
            return "login";
        }

        // If auth succeeds, render a new view
        if (authenticator.authenticate(user.getUsername(), user.getPassword())) {
            model.addAttribute(Attribute.LOGGED_IN_USER, user);
            log.log(Level.INFO, "Authenticate successful");
            if(user.getUsername().equals("rodas")){
                return "rodas";
            }
            else{
                return "redirect:/main";
            }

            // If auth fails, render the same view with error message
        } else {
            model.addAttribute("error", "Authentication Failure");
            log.log(Level.WARNING, "ERROR AUTHENTICATING");
            return "login";
        }

    }

    @RequestMapping(method = RequestMethod.GET, value = "/register" )
    public ModelAndView registerNew() {

        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject(Attribute.USER, new User());
        return modelAndView;
    }

}