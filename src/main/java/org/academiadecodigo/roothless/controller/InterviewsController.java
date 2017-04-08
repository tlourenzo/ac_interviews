package org.academiadecodigo.roothless.controller;

import org.academiadecodigo.roothless.auth.Attribute;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Created by tlourenzo on 08-04-2017.
 */
@Controller
@SessionAttributes(Attribute.LOGGED_IN_USER)
public class InterviewsController {
}
